package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.User;

import java.util.List;

public interface OrderService {
    public List<OrderDTO> getAllOrders();
    public OrderDTO getOrderById(long orderId) throws Throwable;
    public OrderDTO addOrder(SaveOrderDTO orderDTO) throws Throwable;
    public void updateOrder(Order order);

    public void deleteOrder(long orderId) throws Exception;

}
