package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminRepository;
import com.example.demo.model.Admin;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public void addLog(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin showLog(int acctID) {
        return adminRepository.findById(acctID).orElse(null);
    }

    public void deleteLog(int acctID) {
        adminRepository.deleteById(acctID);
    }
}
