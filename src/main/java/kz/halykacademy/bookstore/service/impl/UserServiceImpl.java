package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.UserRole;
import kz.halykacademy.bookstore.errors.AuthorizationException;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.errors.UserAlreadyRegistered;
import kz.halykacademy.bookstore.mapper.UserMapper;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

  /*  @PostConstruct
    public  void init(){
        Optional<User> admin = userRepository.findByLogin("admin");
        if(admin.isEmpty()){
            userRepository.saveAndFlush(
                    new User(
                            null,
                            "admin",
                            encoder.encode("admin"),
                            null,
                            UserRole.ADMIN,
                            false
                    )
            );
        }
    }*/


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
    public UserDTO addUser(UserDTO userDTO)  {

        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new IllegalArgumentException("user already exist");
        }

        User saveUser = userRepository.saveAndFlush(
                new User(
                        userDTO.getUser_id(),
                        userDTO.getLogin(),
                        encoder.encode(userDTO.getPassword()),
                        null,
                        UserRole.USER,
                        false
                )
        );
        return userMapper.toDTO(saveUser);

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws Throwable {
        User user = userRepository.findById(userDTO.getUser_id()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user with id %s not founded".formatted(userDTO.getUser_id())));
        User updateUser = userRepository.save(
                new User(
                        userDTO.getUser_id(),
                        userDTO.getLogin(),
                        encoder.encode(userDTO.getPassword()),
                        user.getOrder(),
                        userDTO.getRole(),
                        userDTO.isBlocked()
                )
        );
        return userMapper.toDTO(updateUser);
    }

    @Override
    public void deleteUser(Long userId) throws Throwable {

        userRepository.findById(userId).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user with id %s not founded".formatted(userId)));
        userRepository.deleteById(userId);
    }

}
