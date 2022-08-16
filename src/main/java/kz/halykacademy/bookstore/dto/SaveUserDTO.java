package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SaveUserDTO  {

    private String login;
    private String password;

}

