package com.nr.chatapp.controller;

import com.nr.chatapp.dto.LoginResponse;
import com.nr.chatapp.dto.LoginVo;
import com.nr.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginVo loginVo) {
        LoginResponse loginResponse = userService.login(loginVo);
        return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
    }

}
