package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.dto.UpdateOrderByAdminDTO;

import java.util.List;

public interface OrderService {
    public List<OrderDTO> getAllOrders();
    public OrderDTO getOrderById(Long orderId) throws Throwable;
    public OrderDTO addOrder(SaveOrderDTO orderDTO) throws Throwable;
    public OrderDTO updateOrderByAdmin( UpdateOrderByAdminDTO orderDTO) throws Throwable;
    public OrderDTO updateOrderByUser( SaveOrderDTO orderDTO) throws Throwable;
    public void deleteOrder(Long orderId) throws Throwable;




}
