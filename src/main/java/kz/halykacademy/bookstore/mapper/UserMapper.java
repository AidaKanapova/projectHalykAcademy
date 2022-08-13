package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUser_id(),
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                user.isBlocked()
        );
    }
}
