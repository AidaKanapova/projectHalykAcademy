package kz.halykacademy.bookstore.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderByAdminDTO {

    private Long orderId;
    private String status;

}
