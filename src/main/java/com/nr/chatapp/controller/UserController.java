package com.nr.chatapp.controller;

import com.nr.chatapp.dto.LoginResponse;
import com.nr.chatapp.dto.LoginVo;
import com.nr.chatapp.dto.UserVo;
import com.nr.chatapp.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    private  UserService userService;


    @GetMapping("/test")
    public String test() {
        return "it is working ";
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@Valid  @RequestBody UserVo userVo) {
        System.out.println("inside add user");
        return new ResponseEntity<>(userService.addUser(userVo), HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UserVo userVo, @PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(userService.updateUser(userVo, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Integer userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.CREATED);
    }


}
