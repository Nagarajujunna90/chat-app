package com.nr.chatapp.service;

import com.nr.chatapp.dto.UserVo;
import com.nr.chatapp.model.User;
import com.nr.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(UserVo userVo) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapping(userVo, user);
        return userRepository.save(user);
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

    private  void userMapping(UserVo userVo, User user) {
        user.setName(userVo.getName());
        user.setAge(userVo.getAge());
        user.setGender(userVo.getGender());
        user.setLanguage(userVo.getLanguage());
        user.setCountry(userVo.getCountry());
        user.setEmailId(userVo.getEmailId());
    }

}
