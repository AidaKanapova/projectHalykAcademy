package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.dto.UpdateOrderDTO;
import kz.halykacademy.bookstore.entity.*;
import kz.halykacademy.bookstore.errors.AuthorizationException;
import kz.halykacademy.bookstore.errors.OrderInvalidValueException;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.BookMapper;
import kz.halykacademy.bookstore.mapper.OrderMapper;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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

        List<Books> books = bookRepository.getBooksByListId(orderDTO.getBookList());

        if (books.isEmpty()) {
            throw new OrderInvalidValueException("order cannot be empty");
        }
        for (Books book : books) {
            if (book.isDeleted()) {
                throw new OrderInvalidValueException("book with id %s deleted".formatted(book.getBookId()));
            }
        }
        List<Integer> sumList = books.stream().map(Books::getPrice).toList();

        int sum = (sumList.stream().mapToInt(a -> a).sum());
        if (sum > 10000) {
            throw new OrderInvalidValueException("order exceeded the limit");
        }
        Order saveOrder = orderRepository.saveAndFlush(
                new Order(
                        orderDTO.getOrderId(),
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
    public OrderDTO updateOrderByAdmin(UpdateOrderDTO orderDTO) throws Throwable {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = orderRepository.findById(orderDTO.getOrderId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("order with id %s not founded".formatted(orderDTO.getOrderId())));

        User user = userRepository.findById(orderDTO.getUserId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user with id %s not founded".formatted(orderDTO.getUserId())));
        if (!userDetails.getAuthorities().contains(UserRole.ADMIN)) {
            throw new IllegalArgumentException("you are not allowed to change status of the order");
        }
        if (user.isBlocked()) {
            throw new OrderInvalidValueException("user with id %s blocked".formatted(orderDTO.getUserId()));
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
    public OrderDTO updateOrderByUser(UpdateOrderDTO orderDTO) throws Throwable {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user with id %s not founded".formatted(orderDTO.getUserId())));  //id юзера который вошел
        Order order = orderRepository.findById(orderDTO.getOrderId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("order with id %s not founded".formatted(orderDTO.getOrderId()))); // id юзера в заказе

        if (Objects.equals(user.getUser_id(), order.getUser().getUser_id())) {
            if (order.getStatus().name().contains("CREATED")) {

                List<Books> books = bookRepository.getBooksByListId(orderDTO.getBookList());

                if (books.isEmpty()) {
                    throw new OrderInvalidValueException("order cannot be empty");
                }
                for (Books book : books) {
                    if (book.isDeleted()) {
                        throw new OrderInvalidValueException("book with id %s deleted".formatted(book.getBookId()));
                    }
                }
                List<Integer> sumList = books.stream().map(Books::getPrice).toList();

                int sum = (sumList.stream().mapToInt(a -> a).sum());
                if (sum > 10000) {
                    throw new OrderInvalidValueException("order amount exceeded the limit of 10.000");
                }

                Order updateOrderList = orderRepository.saveAndFlush(
                        new Order(
                                orderDTO.getOrderId(),
                                order.getUser(),
                                books,
                                sum,
                                order.getStatus(),
                                order.getCreated()
                        )
                );
                return orderMapper.toDTO(updateOrderList);
            } else
                throw new OrderInvalidValueException("status of the order %s. you are no longer allowed to change the order".formatted(order.getStatus()));
        } else throw new IllegalArgumentException("its not your order");

    }


    @Override
    public void deleteOrder(Long orderId) throws Throwable {
        orderRepository.findById(orderId).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("order not founded"));
        orderRepository.deleteById(orderId);
    }
}
