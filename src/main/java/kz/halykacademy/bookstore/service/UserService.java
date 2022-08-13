package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.errors.UserAlreadyRegistered;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(Long userId) throws Throwable;
    public UserDTO addUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO) throws  Throwable ;

    public void deleteUser(Long userId) throws Throwable;



}
