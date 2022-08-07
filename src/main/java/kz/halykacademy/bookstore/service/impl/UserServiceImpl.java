package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.UserRole;
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
                        null,
                        userDTO.getRole(),
                        userDTO.isBlocked()
                )
        );
        return  user.userDTO();
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO,long id) {
        User user = userRepository.findById(id).get();
        User saveUser = userRepository.save(
                new User(
                        user.getUser_id(),
                        userDTO.getLogin(),
                        userDTO.getPassword(),
                        user.getOrder(),
                        userDTO.getRole(),
                        userDTO.isBlocked()

                )
        );
        return saveUser.userDTO();
    }

    @Override
    public void deleteUser(long userId) throws Exception {

        userRepository.deleteById(userId);
    }

  /*  public Optional<User> findByLogin(String login){
        Optional<User> user = userRepository.findByLogin(login);
        return user;
    }*/
}
