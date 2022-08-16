package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.SaveUserDTO;
import kz.halykacademy.bookstore.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long userId) throws Throwable;
    UserDTO addUser(SaveUserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO) throws  Throwable ;

    void deleteUser(Long userId) throws Throwable;



}
