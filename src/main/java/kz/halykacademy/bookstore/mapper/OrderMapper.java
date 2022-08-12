package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.BookNameDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final BookMapper bookMapper;
    private final OrderRepository orderRepository;

    public OrderDTO toDTO(Order order) {
        List<BookNameDTO> books = List.of();
        if (order.getBooks() != null)
            books = order.getBooks().stream().map(bookMapper::toBookDTO).collect(Collectors.toList());

        List<Integer> sum = List.of();
        if(order.getBooks() != null)
            sum = order.getBooks().stream().map(Books::getPrice).collect(Collectors.toList());
        int summ = sum.stream().mapToInt(a ->a).sum();
        return new OrderDTO(
                order.getOrderId(),
                order.getUser().getLogin(),
                books,
                summ,
                order.getStatus().toString()
        );

    }


}
