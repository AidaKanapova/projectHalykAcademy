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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        if (user.isBlocked()) {
            throw new AuthorizationException("blocked user");
        }

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
        if (userDetails.getAuthorities().contains(UserRole.ADMIN)) {

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

        } else throw new IllegalArgumentException("you cant change order");


        }

    @Override
    public OrderDTO updateOrderByUser(UpdateOrderDTO orderDTO) throws Throwable {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        long userId = userRepository.findByLogin(userDetails.getUsername()).get().getUser_id();  //id юзера который вошел
        long foundUserIdOnOrder = orderRepository.findById(orderDTO.getOrderId()).get().getUser().getUser_id(); // id юзера в заказе

        if (userId == foundUserIdOnOrder) {

            Order order = orderRepository.findById(orderDTO.getOrderId()).orElseThrow((Supplier<Throwable>) () ->
                    new ResourceNotFoundeException("order with id %s not founded".formatted(orderDTO.getOrderId())));
            List<Books> addedBooks = bookRepository.getBooksByListId(orderDTO.getAddedBooks());
            List<Books> deletedBooks = bookRepository.getBooksByListId(orderDTO.getDeletedBooks());

            order.getBooks().addAll(addedBooks);
            order.getBooks().removeAll(deletedBooks);

            Order updateOrderList = orderRepository.saveAndFlush(
                    new Order(
                    orderDTO.getOrderId(),
                    order.getUser(),
                    order.getBooks(),
                    order.getSum(),
                    order.getStatus(),
                    order.getCreated()
                    )
            );
            return orderMapper.toDTO(updateOrderList);
        } else throw new IllegalArgumentException("its not your order");
    }

    @Override
    public OrderDTO addBookToOrder(Long orderId, Long bookId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        long userId = userRepository.findByLogin(userDetails.getUsername()).get().getUser_id();  //id юзера который вошел
        long foundUserIdOnOrder = orderRepository.findById(orderId).get().getUser().getUser_id(); // id юзера в заказе

        if (userId == foundUserIdOnOrder && userDetails.getAuthorities().contains(UserRole.USER)) {

            Order order = orderRepository.findById(orderId).get();
            Books books = bookRepository.findById(bookId).get();

            order.addBook(books);

            return orderMapper.toDTO(orderRepository.save(order));
        } else {
            throw new IllegalArgumentException("its not your order");
        }
    }

    @Override
    public void deleteOrder(Long orderId) throws Throwable {
        orderRepository.findById(orderId).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("order not founded"));
        orderRepository.deleteById(orderId);
    }


}
