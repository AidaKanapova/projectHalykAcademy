package kz.halykacademy.bookstore.service.impl;


import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.OrderStatus;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {

    private  final OrderRepository orderRepository;
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
        if(user.isBlocked()==true){throw new IllegalArgumentException("blocked user");}
        else{

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
    public void updateOrder(Order order) {
        orderRepository.save(order);


    }

    @Override
    public void deleteOrder(long orderId) throws Exception {

        orderRepository.deleteById(orderId);
    }



}
