package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream().map(User::userDTO).toList();
    }

    @Override
    public UserDTO getUserById(long userId) throws Throwable {
        return userRepository.findById(userId)
                .map(User::userDTO)
                .orElseThrow((Supplier< Throwable>) () ->
                        new ResourceNotFoundeException("user not founded".formatted(userId)));
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = userRepository.save(
                new User(
                        userDTO.getUser_id(),
                        userDTO.getLogin(),
                        userDTO.getPassword(),
                        userDTO.getRole(),
                        userDTO.isBlocked()
                )
        );
        return  user.userDTO();
    }

    @Override
    public void updateUser(User users) {
        userRepository.save(users);


    }

    @Override
    public void deleteUser(long userId) throws Exception {

        userRepository.deleteById(userId);
    }
}
