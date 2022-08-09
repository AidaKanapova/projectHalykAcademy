package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.UserDTO;

import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private  final UserService userService;


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

    @PutMapping("/updateUser/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable long id){
        return userService.updateUser(userDTO,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") long userId) throws Exception {
        userService.deleteUser(userId);
    }
}
