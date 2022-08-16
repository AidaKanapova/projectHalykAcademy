package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.SaveUserDTO;
import kz.halykacademy.bookstore.dto.UpdateUserByAdminDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.UserRole;
import kz.halykacademy.bookstore.errors.InvalidValueException;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
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
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @PostConstruct
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
                            false,
                            false
                    )
            );
        }
    }

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
                        UserRole.USER,
                        false,
                        false
                )
        );
        return userMapper.toDTO(saveUser);

    }

    @Override
    public UserDTO updateUserByAdmin(UpdateUserByAdminDTO userDTO) throws Throwable {

       User user = userRepository.findById(userDTO.getId()).orElseThrow((Supplier<Throwable>) () ->
                new ResourceNotFoundeException("user with id %s not founded".formatted(userDTO.getId())));

        User updateUser = userRepository.save(
                new User(
                        user.getId(),
                        user.getLogin(),
                        user.getPassword(),
                        user.getOrder(),
                        userDTO.getRole(),
                        userDTO.isBlocked(),
                        user.isDeleted()
                )
        );
        return userMapper.toDTO(updateUser);
    }

    @Override
    public UserDTO updateUserByUser(UserDTO userDTO) throws Throwable {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow((Supplier<Throwable>) () ->
                new IllegalArgumentException("you need to log in"));
        if (Objects.equals(user.getId(), userDTO.getId())) {
            if (userRepository.existsByLogin(userDTO.getLogin())) {
                throw new InvalidValueException("user with this login already exist");
            }
            User updateUser = userRepository.save(
                    new User(
                            userDTO.getId(),
                            userDTO.getLogin(),
                            encoder.encode(userDTO.getPassword()),
                            user.getOrder(),
                            user.getRole(),
                            user.isBlocked(),
                            user.isDeleted()
                    )
            );
            return userMapper.toDTO(updateUser);
        } else throw new InvalidValueException("you cant change user");
    }

    @Override
    public void deleteUser(Long userId)  {
        userRepository.deleteById(userId);

    }

}
