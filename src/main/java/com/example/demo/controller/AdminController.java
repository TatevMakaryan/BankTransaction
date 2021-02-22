package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;


    public void addLog(Admin admin) {
        adminService.addLog(admin);
    }


    @GetMapping("/account/{acctID}/logs")
    public Admin showLog(@PathVariable int acctID) {
        return adminService.showLog(acctID);
    }

    public void deleteLog(int acctID) {
        adminService.deleteLog(acctID);
    }
}

