package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.dto.UpdateOrderByAdminDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long orderId) throws Throwable;
    OrderDTO addOrder(SaveOrderDTO orderDTO) throws Throwable;
    OrderDTO updateOrderByAdmin( UpdateOrderByAdminDTO orderDTO) throws Throwable;
    OrderDTO updateOrderByUser( SaveOrderDTO orderDTO) throws Throwable;
    void deleteOrder(Long orderId) throws Throwable;




}
