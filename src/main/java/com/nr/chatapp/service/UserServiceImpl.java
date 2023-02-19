package com.nr.chatapp.service;

import com.nr.chatapp.config.JwtUtil;
import com.nr.chatapp.dto.LoginResponse;
import com.nr.chatapp.dto.LoginVo;
import com.nr.chatapp.dto.UserVo;
import com.nr.chatapp.exception.UserNameAlreadyExist;
import com.nr.chatapp.model.User;
import com.nr.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(UserVo userVo) {
        User byUserName = userRepository.findByUserName(userVo.getUserName());
        if (byUserName == null) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(userVo.getPassword()));
            userMapping(userVo, user);
            return userRepository.save(user);
        } else {
            throw new UserNameAlreadyExist();
        }
    }


    @Override
    public User updateUser(UserVo userVo, Integer userId) {
        User user = new User();
        user.setId(userId);
        userMapping(userVo, user);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public LoginResponse login(LoginVo loginVo) {
        LoginResponse loginResponse = new LoginResponse();
        User user = userRepository.findByUserName(loginVo.getUserName());
        boolean isPasswordMatched = passwordEncoder.matches(loginVo.getPassword(), user.getPassword());
        if (isPasswordMatched) {
            String token = jwtUtil.generateToken(loginVo.getUserName());
            loginResponse.setAccessToken(token);
            loginResponse.setName(user.getName());
            loginResponse.setCountry(user.getCountry());
        }
        return loginResponse;
    }

    private void userMapping(UserVo userVo, User user) {
        user.setName(userVo.getName());
        user.setUserName(userVo.getUserName());
        user.setAge(userVo.getAge());
        user.setGender(userVo.getGender());
        user.setLanguage(userVo.getLanguage());
        user.setCountry(userVo.getCountry());
        user.setEmailId(userVo.getEmailId());
    }

}
