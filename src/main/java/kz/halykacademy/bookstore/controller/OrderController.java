package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    private  final BookRepository bookRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/allOrders")
    public List<OrderDTO> getAllOrders() {
        return  orderService.getAllOrders();
    }

    @GetMapping("/getById/{id}")
    public OrderDTO geOrderById(@PathVariable("id") long orderId) throws Throwable {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/addOrder")
    public OrderDTO addOrder(@RequestBody SaveOrderDTO orderDTO) throws Throwable {
        return  orderService.addOrder(orderDTO);
    }

    @PutMapping("/updateOrder")
    public void updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") long orderId) throws Exception {
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}/book/{bookId}")
    public Order addBookToOrder(@PathVariable long orderId, @PathVariable long bookId) {
        Order order = orderRepository.findById(orderId).get();
        Books books = bookRepository.findById(bookId).get();
        order.addBook(books);
        return  orderRepository.save(order);
    }
}
