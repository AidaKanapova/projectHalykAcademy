package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.UserRole;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private Long user_id;
    private String login;
    private String password;
    private UserRole role;
    private boolean blocked;
}


