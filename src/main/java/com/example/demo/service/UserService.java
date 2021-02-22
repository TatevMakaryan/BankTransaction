package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getUserInfo(int acctID) {
        return userRepository.findById(acctID).orElse(null);
    }

    public void deleteUser(int acctID) {
        userRepository.deleteById(acctID);
    }

}
