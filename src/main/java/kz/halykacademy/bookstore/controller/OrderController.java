package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.dto.UpdateOrderByAdminDTO;
import kz.halykacademy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/allOrders")
    public List<OrderDTO> getAllOrders() {
        return  orderService.getAllOrders();
    }

    @GetMapping("/getById/{id}")
    public OrderDTO geOrderById(@PathVariable("id") Long orderId) throws Throwable {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/addOrder")
    public OrderDTO addOrder(@RequestBody SaveOrderDTO orderDTO) throws Throwable {
        return  orderService.addOrder(orderDTO);
    }

    @PutMapping("/updateOrder")
    public OrderDTO updateOrder(@RequestBody SaveOrderDTO newOrder) throws Throwable {

        return orderService.updateOrderByUser(newOrder);
    }

    @PutMapping("/updateOrderByAdmin")
    public OrderDTO updateOrderByAdmin(@RequestBody UpdateOrderByAdminDTO newOrder) throws Throwable {

        return orderService.updateOrderByAdmin(newOrder);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") Long orderId) throws Throwable {
        orderService.deleteOrder(orderId);
    }

}
