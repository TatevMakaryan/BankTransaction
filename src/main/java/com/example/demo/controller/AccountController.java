package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Accounts;
import com.example.demo.model.Admin;
import com.example.demo.service.AccountService;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AdminController adminController;


    public void createAccount(int acctID, int balance, String acctStatus) {
        Accounts acct = new Accounts(acctID, balance, acctStatus);
        accountService.createAccount(acct);
    }


    @GetMapping("/account/{acctID}/balance")
    public int getBalance(@PathVariable int acctID) {
        return accountService.getBalance(acctID);
    }


    @PutMapping("/account/{acctID}/deposit/{amount}")
    public void depositAmount(@PathVariable int acctID, @PathVariable int amount) {
        int initBal = getBalance(acctID);
        accountService.depositAmount(acctID, amount);
        Admin admin = new Admin(acctID, "Deposited", "Success", initBal, initBal + amount);
        adminController.addLog(admin);
    }


    @PutMapping("/account/{acctID}/withdraw/{amount}")
    public void withdrawAmount(@PathVariable int acctID, @PathVariable int amount) {
        int initBal = getBalance(acctID);
        accountService.withdrawAmount(acctID, amount);
        Admin admin = new Admin(acctID, "Withdrawn", "Success", initBal, initBal - amount);
        adminController.addLog(admin);
    }


    @PutMapping("/account/{acctID}/transfer/{destAcctID}/{amount}")
    public void transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
        int initBalSender = getBalance(acctID);
        int initBalReceiver = getBalance(destAcctID);
        accountService.transferAmount(acctID, destAcctID, amount);
        Admin adminSender = new Admin(acctID, "Transferred", "Success", initBalSender, initBalSender - amount);
        adminController.addLog(adminSender);
        Admin adminReceiver = new Admin(destAcctID, "Received", "Success", initBalReceiver,
                initBalReceiver + amount);
        adminController.addLog(adminReceiver);
    }


    @DeleteMapping("/account/{acctID}")
    public void deleteAccount(@PathVariable int acctID) {
        accountService.deleteAccount(acctID);
        adminController.deleteLog(acctID);
    }


    @GetMapping("/account/{acctID}")
    public Accounts getAccountInfo(@PathVariable int acctID) {
        return accountService.getAccountInfo(acctID);
    }

}
