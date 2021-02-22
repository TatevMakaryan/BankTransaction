package com.example.demo.controller;


import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountController accountController;

    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
        accountController.createAccount(user.getAcctID(), 0, "Active");
    }

    @GetMapping("/user/{acctID}")
    public User getUserInfo(@PathVariable int acctID) {
        return userService.getUserInfo(acctID);
    }

    @DeleteMapping("/user/{acctID}")
    public void deleteUser(@PathVariable int acctID) {
        userService.deleteUser(acctID);
    }

}


