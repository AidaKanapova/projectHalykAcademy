package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long order_id;
    private String user;
    private List<BookNameDTO> books;
    private int sum;
    private String status;

}