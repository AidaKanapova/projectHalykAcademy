package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.entity.User;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(long userId) throws Throwable;
    public UserDTO addUser(UserDTO userDTO);
    public void updateUser(User userDTO);

    public void deleteUser(long userId) throws Exception;



}
