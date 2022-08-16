package kz.halykacademy.bookstore.dto;


import kz.halykacademy.bookstore.entity.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserByAdminDTO {

    private Long id;
    private UserRole role;
    private boolean blocked;
}

