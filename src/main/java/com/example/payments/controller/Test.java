package com.example.payments.controller;

import com.example.payments.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    CustomerService customerService;
    @GetMapping("/testing")
    public boolean test(){
        return customerService.isCustomerIdExist(456L);
    }
}
