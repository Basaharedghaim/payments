package com.example.payments.controller;

import com.example.payments.exceptions.CustomerNotFoundException;
import com.example.payments.services.CustomerService;
import com.example.payments.services.PaymentService;
import com.models.demo.models.entity.Request;
import com.models.demo.models.entity.Response;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/paymentsClient")
public class PaymentsController {
    final private PaymentService paymentService;
    final private CustomerService customerService;
    @Autowired
    public PaymentsController(PaymentService paymentService,CustomerService customerService){
this.paymentService=paymentService;
this.customerService=customerService;
    }
    @PostMapping("/buyBook")
    public String processPayments(@RequestBody Request request) {
        Long id = request.getAccount().getCustomer().getId();
        if (customerService.isCustomerIdExist(id)) {
            System.out.println("Customer exists: " + id);
            return paymentService.processPayment(request);
        } else {
            System.out.println("Customer does not exist: " + id);
            throw new CustomerNotFoundException("Customer Not Found: " + id);
        }
    }

    @GetMapping("/try")
    public String test1(){
        return "from Payments";
    }

}
