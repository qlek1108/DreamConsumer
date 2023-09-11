package com.example.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/sign-up")
    public ResponseEntity postUser(@RequestBody UserPostDto userPostDto) {
        User user = mapper.UserPostDtoToUser(userPostDto);
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(mapper.UserToUserResponseDto(createdUser), HttpStatus.CREATED);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Min(1) long userId) {
        User user = userService.findUser(userId);
        //mypageDto로 바꿔야 함
        return new ResponseEntity<>(mapper.UserToUserResponseDto(user), HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Min(1) long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/activate/{user-id}")
    public ResponseEntity activateUser(@PathVariable("user-id") @Min(1) long userId) {
        userService.activateUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
