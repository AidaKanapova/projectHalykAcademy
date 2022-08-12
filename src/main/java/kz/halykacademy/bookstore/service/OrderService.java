package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.dto.UpdateOrderDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.User;

import java.util.List;

public interface OrderService {
    public List<OrderDTO> getAllOrders();
    public OrderDTO getOrderById(Long orderId) throws Throwable;
    public OrderDTO addOrder(SaveOrderDTO orderDTO) throws Throwable;
    public OrderDTO updateOrderByAdmin( UpdateOrderDTO orderDTO) throws Throwable;
    public OrderDTO updateOrderByUser( UpdateOrderDTO orderDTO) throws Throwable;

    public OrderDTO addBookToOrder(Long orderId, Long bookId) ;

    public void deleteOrder(Long orderId) throws Throwable;




}
