package com.example.demo.user.controller;

import com.example.demo.user.entity.User;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.dto.UserPostDto;
import com.example.demo.user.service.UserService;
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
        return new ResponseEntity<>(mapper.UserToUserMyPageResponseDto(user), HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Min(1) long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/activate/{user-id}")
    public ResponseEntity activateUser(@PathVariable("user-id") @Min(1) long userId) {
        userService.activateUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
