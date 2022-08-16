package kz.halykacademy.bookstore.controller;
import kz.halykacademy.bookstore.dto.SaveUserDTO;
import kz.halykacademy.bookstore.dto.UpdateUserByAdminDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private  final UserService userService;


    @GetMapping("/allUsers")
    public List<UserDTO> getAllUsers() {
        return  userService.getAllUsers();
    }

    @GetMapping("/getById/{id}")
    public UserDTO geUserById(@PathVariable("id") Long userId) throws Throwable {
        return userService.getUserById(userId);
    }

    @PostMapping("/addUser")
    public UserDTO addUser(@RequestBody SaveUserDTO userDTO) {
        return  userService.addUser(userDTO);
    }

    @PutMapping("/updateUser")
    public UserDTO updateUserByUser(@RequestBody UserDTO userDTO) throws Throwable{
        return userService.updateUserByUser(userDTO);
    }
    @PutMapping("/updateUserByAdmin")
    public UserDTO updateUserByAdmin(@RequestBody UpdateUserByAdminDTO userDTO) throws Throwable{
        return userService.updateUserByAdmin(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long userId) throws Throwable {
        userService.deleteUser(userId);
    }
}
