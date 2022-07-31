package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/allUsers")
    public List<UserDTO> getAllUsers() {
        return  userService.getAllUsers();
    }

    @GetMapping("/getById/{id}")
    public UserDTO geUserById(@PathVariable("id") long userId) throws Throwable {
        return userService.getUserById(userId);
    }

    @PostMapping("/addUser")
    public UserDTO addBook(@RequestBody UserDTO userDTO) throws Throwable {
        return  userService.addUser(userDTO);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") long userId) throws Exception {
        userService.deleteUser(userId);
    }
}
