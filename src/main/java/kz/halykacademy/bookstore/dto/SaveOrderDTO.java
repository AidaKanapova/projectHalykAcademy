package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.OrderStatus;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveOrderDTO {

    private long orderId;
    private long userId;
    private String status;

}
