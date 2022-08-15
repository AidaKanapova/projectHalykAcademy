package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.dto.UpdateOrderByAdminDTO;
import kz.halykacademy.bookstore.entity.*;
import kz.halykacademy.bookstore.errors.InvalidValueException;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.BookMapper;
import kz.halykacademy.bookstore.mapper.OrderMapper;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.StockRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderMapper orderMapper;
    private final BookMapper bookMapper;
    private final StockRepository stockRepository;


    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDTO).toList();
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundeException("order not founded"));
    }

    @Override
    public OrderDTO addOrder(SaveOrderDTO orderDTO) throws Throwable {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user not founded"));

        List<Books> books = checkOrder(orderDTO);
        int sum = checkSum(books, orderDTO);

        Order saveOrder = orderRepository.saveAndFlush(
                new Order(
                        orderDTO.getId(),
                        user,
                        books,
                        sum,
                        OrderStatus.CREATED,
                        LocalDate.now()
                )
        );
        return orderMapper.toDTO(saveOrder);
    }

    @Override
    public OrderDTO updateOrderByAdmin(UpdateOrderByAdminDTO orderDTO) throws Throwable {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = orderRepository.findById(orderDTO.getOrderId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("order with id %s not founded".formatted(orderDTO.getOrderId())));

        User user = userRepository.findById(orderDTO.getUserId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user with id %s not founded".formatted(orderDTO.getUserId())));
        if (!userDetails.getAuthorities().contains(UserRole.ADMIN)) {
            throw new IllegalArgumentException("you are not allowed to change status of the order");
        }
        if (user.isBlocked()) {
            throw new InvalidValueException("user with id %s blocked".formatted(orderDTO.getUserId()));
        }
        Order updateOrderStatus = orderRepository.saveAndFlush(
                new Order(
                        order.getOrderId(),
                        order.getUser(),
                        order.getBooks(),
                        order.getSum(),
                        OrderStatus.valueOf(orderDTO.getStatus()),
                        order.getCreated()
                )
        );
        return orderMapper.toDTO(updateOrderStatus);

    }

    @Override
    public OrderDTO updateOrderByUser(SaveOrderDTO orderDTO) throws Throwable {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("you need to log in"));
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("order with id %s not founded".formatted(orderDTO.getId())));

        if (Objects.equals(user.getId(), order.getUser().getId())) {
            if (order.getStatus().name().contains("CREATED")) {

                List<Books> books = checkOrder(orderDTO);
                int sum = checkSum(books, orderDTO);

                Order updateOrderList = orderRepository.saveAndFlush(
                        new Order(
                                orderDTO.getId(),
                                order.getUser(),
                                books,
                                sum,
                                order.getStatus(),
                                order.getCreated()
                        )
                );
                return orderMapper.toDTO(updateOrderList);
            } else
                throw new InvalidValueException("status of the order %s. you are no longer allowed to change the order".formatted(order.getStatus()));
        } else throw new IllegalArgumentException("its not your order");

    }


    @Override
    public void deleteOrder(Long orderId) throws Throwable {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("you need to log in"));
        Order order = orderRepository.findById(orderId).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("order not founded"));
        if (Objects.equals(user.getId(), order.getUser().getId())) {

            if (order.getStatus().name().contains("CREATED")) {
                orderRepository.deleteById(orderId);
            } else throw new IllegalArgumentException("you can no longer delete the order");

        } else throw new IllegalArgumentException("its not your order");
    }

    private List<Books> checkOrder(SaveOrderDTO orderDTO) {
        List<Books> books = orderDTO.getBookList().stream().map(bookRepository::getOne).toList();

        if (books.isEmpty()) {
            throw new InvalidValueException("order cannot be empty");
        }
        for (Books book : books) {
            if (book.isDeleted()) {
                throw new InvalidValueException("book with id %s deleted".formatted(book.getId()));
            }
        }
        return books;
    }

    private int checkSum(List<Books> books, SaveOrderDTO orderDTO) {
        List<Integer> sumList = books.stream().map(Books::getPrice).toList();

        int sum = sumList.stream().mapToInt(a -> a).sum();
        if (sum > 10000) {
            throw new InvalidValueException("order exceeded the limit");
        }

        Map<Long, Long> bookCount = orderDTO.getBookList().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Long> count = bookCount.values().stream().toList();
        for (Map.Entry<Long, Long> entry : bookCount.entrySet()) {
            if (stockRepository.getBookCount(entry.getKey()) - entry.getValue() < 0) {
                throw new InvalidValueException("all books with id %s send".formatted(entry.getKey()));
            }
            stockRepository.updateCount(entry.getValue(), entry.getKey());

        }
        return sum;
    }


}
