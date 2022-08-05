package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    private  final BookRepository bookRepository;
    private  final UserRepository userRepository;



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

    @PostMapping("/updateOrder/{orderId}")
    public void updateOrder(@RequestBody SaveOrderDTO newOrder,
                                           @PathVariable("orderId") long orderId) throws Throwable {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        long userId = userRepository.findByLogin(userDetails.getUsername()).get().getUser_id();  //id юзера который вошел
        long foundUserIdOnOrder = orderRepository.findById(orderId).get().getUser().getUser_id(); // id юзера в заказе


        if(userId == foundUserIdOnOrder){
        orderService.updateOrder(newOrder, orderId);}
        else {throw  new ResourceNotFoundeException("not your order");
        }
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
