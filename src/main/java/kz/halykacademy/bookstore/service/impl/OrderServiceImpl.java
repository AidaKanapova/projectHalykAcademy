package kz.halykacademy.bookstore.service.impl;


import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.entity.*;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(Order::orderDTO).toList();
    }

    @Override
    public OrderDTO getOrderById(long orderId) throws Throwable {
        return orderRepository.findById(orderId)
                .map(Order::orderDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("order not founded".formatted(orderId)));
    }

    @Override
    public OrderDTO addOrder(SaveOrderDTO orderDTO) throws Throwable {

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("user not founded"));

        Order order = null;
        if (user.isBlocked()) {
            throw new IllegalArgumentException("blocked user");
        } else {

            order = orderRepository.save(
                    new Order(
                            orderDTO.getOrderId(),
                            user,
                            null,
                            0,
                            OrderStatus.CREATED,
                            LocalDate.now()
                    )
            );
        }
        return order.orderDTO();
    }

    @Override
    public OrderDTO updateOrder(SaveOrderDTO orderDTO, long orderId) throws Throwable {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails.getAuthorities().contains(UserRole.ADMIN)) {

            Order order = orderRepository.findById(orderId).get();


            Order updateOrder = new Order(
                    order.getOrderId(),
                    order.getUser(),
                    order.getBooks(),
                    order.getSum(),
                    OrderStatus.valueOf(orderDTO.getStatus()),
                    order.getCreated()
            );

            return updateOrder.orderDTO();


        } else {
            throw new IllegalArgumentException("you cant change status of order");
        }


    }

    @Override
    public OrderDTO addBook(long orderId, long bookId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        long userId = userRepository.findByLogin(userDetails.getUsername()).get().getUser_id();  //id юзера который вошел
        long foundUserIdOnOrder = orderRepository.findById(orderId).get().getUser().getUser_id(); // id юзера в заказе


        if (userId == foundUserIdOnOrder && userDetails.getAuthorities().contains(UserRole.USER)) {

            Order order = orderRepository.findById(orderId).get();
            Books books = bookRepository.findById(bookId).get();

            order.addBook(books);

            return orderRepository.save(order).orderDTO();
        } else {
            throw new IllegalArgumentException("its not your order");
        }
    }

        @Override
        public void deleteOrder ( long orderId) throws Exception {

            orderRepository.deleteById(orderId);
        }


    }
