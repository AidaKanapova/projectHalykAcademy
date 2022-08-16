package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveOrderDTO {
    private Long id;
    private List<Long> bookList;
}
