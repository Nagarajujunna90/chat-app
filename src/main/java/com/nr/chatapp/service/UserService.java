package com.nr.chatapp.service;

import com.nr.chatapp.dto.LoginResponse;
import com.nr.chatapp.dto.LoginVo;
import com.nr.chatapp.dto.UserVo;
import com.nr.chatapp.model.User;

import java.util.List;

public interface UserService {

    User addUser(UserVo userVo);

    User updateUser(UserVo userVo, Integer userId);

    User getUserById(Integer userId);

    List<User> getAllUsers();

    void deleteUserById(Integer userId);

    LoginResponse login(LoginVo loginVo);
}
