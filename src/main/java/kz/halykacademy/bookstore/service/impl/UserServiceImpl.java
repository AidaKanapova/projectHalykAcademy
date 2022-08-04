package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
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
    private  final PasswordEncoder encoder;

    @PostConstruct
    public  void init(){
        Optional<User> admin = userRepository.findByLogin("user");
        if(admin.isEmpty()){
            userRepository.saveAndFlush(
                    new User(
                            null,
                            "user",
                            encoder.encode("admin"),
                            "USER",
                            false
                    )
            );
        }
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
        User user = userRepository.saveAndFlush(
                new User(
                        userDTO.getUser_id(),
                        userDTO.getLogin(),
                        encoder.encode(userDTO.getPassword()),
                        userDTO.getRole(),
                        userDTO.isBlocked()
                )
        );
        return  user.userDTO();
    }

    @Override
    public void updateUser(UserDTO user,
                           Long id) {
        /*List<UserDTO> booksList = userRepository.findAll()
                .stream()
                .map(User::userDTO)
                .toList();
        UserDTO foundUser  = booksList.stream()
                .filter(userDTO -> userDTO.getUser_id().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not founded"));

        foundUser.setLogin(user.getLogin());*/
    }

    @Override
    public void deleteUser(long userId) throws Exception {

        userRepository.deleteById(userId);
    }
}
