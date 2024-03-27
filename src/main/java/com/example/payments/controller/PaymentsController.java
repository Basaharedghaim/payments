package com.example.payments.controller;

import com.example.payments.mail.MailSender;
import com.example.payments.services.PaymentService;
import com.models.demo.models.entity.Request;
import com.models.demo.models.entity.Response;
import com.models.demo.models.entity.Status;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/paymentsClient")
public class PaymentsController {
    final private PaymentService paymentService;
    @Autowired
    public PaymentsController(PaymentService paymentService){
this.paymentService=paymentService;
    }
    @PostMapping("/buyBook")
    public Response processPayments(@RequestBody Request request){

            return paymentService.processPayment(request);
    }

}
