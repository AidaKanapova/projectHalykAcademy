package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


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
    public OrderDTO updateOrder(@RequestBody SaveOrderDTO newOrder,
                                           @PathVariable("orderId") long orderId) throws Throwable {

        return orderService.updateOrder(newOrder,orderId);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") long orderId) throws Exception {
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}/book/{bookId}")
    public OrderDTO addBookToOrder(@PathVariable long orderId, @PathVariable long bookId) {
        return orderService.addBookToOrder(orderId,bookId);
    }
}
