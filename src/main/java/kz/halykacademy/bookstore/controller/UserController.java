package kz.halykacademy.bookstore.controller;


import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private  final UserService userService;
    private final UserRepository userRepository;


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

    @PutMapping("/updateUser/{userId}")
   public ResponseEntity<User> updateUser(@RequestBody User newUser,
                                          @PathVariable("userId") long userId){

        return ResponseEntity.ok().body(userService.updateUser(userId,newUser));
    }


  /* public UserDTO updateUser(@RequestBody User userDTO,
                           @PathVariable Long id){
        List<UserDTO> booksList = userRepository.findAll()
                .stream()
                .map(User::userDTO)
                .toList();
        UserDTO foundUser  = booksList.stream()
                .filter(user -> user.getUser_id().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not founded"));

        foundUser.setUser_id(userDTO.getUser_id());
        foundUser.setLogin(userDTO.getLogin());
        foundUser.setPassword(userDTO.getPassword());
        foundUser.setRole(userDTO.getRole());
        foundUser.setBlocked(userDTO.isBlocked());
         return foundUser;
*/




    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") long userId) throws Exception {
        userService.deleteUser(userId);
    }
}
