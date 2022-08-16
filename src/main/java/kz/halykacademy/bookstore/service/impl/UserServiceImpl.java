package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.SaveUserDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.UserRole;
import kz.halykacademy.bookstore.errors.InvalidValueException;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.mapper.UserMapper;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserDTO getUserById(Long userId) throws Throwable {
        return userRepository.findById(userId)
                .map(userMapper::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("user with id %s not founded".formatted(userId)));
    }



    @Override
    public UserDTO addUser(SaveUserDTO userDTO)  {

        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new InvalidValueException("user already exist");
        }

        User saveUser = userRepository.saveAndFlush(
                new User(
                        null,
                        userDTO.getLogin(),
                        encoder.encode(userDTO.getPassword()),
                        null,
                        userDTO.getRole(),
                        false,
                        false
                )
        );
        return userMapper.toDTO(saveUser);

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws Throwable {
        User user = userRepository.findById(userDTO.getId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user with id %s not founded".formatted(userDTO.getId())));
        User updateUser = userRepository.save(
                new User(
                        userDTO.getId(),
                        userDTO.getLogin(),
                        encoder.encode(userDTO.getPassword()),
                        user.getOrder(),
                        userDTO.getRole(),
                        userDTO.isBlocked(),
                        user.isDeleted()
                )
        );
        return userMapper.toDTO(updateUser);
    }

    @Override
    public void deleteUser(Long userId)  {

        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else throw new ResourceNotFoundeException("user with id %s not founded".formatted(userId));
    }

}
