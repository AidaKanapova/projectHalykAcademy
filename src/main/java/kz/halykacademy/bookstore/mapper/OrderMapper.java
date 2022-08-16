package kz.halykacademy.bookstore.mapper;
import kz.halykacademy.bookstore.dto.BookNameDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.SaveOrderDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public OrderDTO toDTO(Order order) {
        List<BookNameDTO> books = List.of();
        if (order.getBooks() != null)
            books = order.getBooks().stream().map(bookMapper::toBookDTO).collect(Collectors.toList());

        return new OrderDTO(
                order.getOrderId(),
                order.getUser().getLogin(),
                books,
                order.getSum(),
                order.getStatus().toString()
        );

    }



}
