package com.example.payments.controller;

import com.example.payments.mail.MailSender;
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
    final private MailSender mailSender;
    @Autowired
    public PaymentsController(MailSender mailSender){
        this.mailSender=mailSender;

    }
    @PostMapping("/buyBook")
    public Response processPayments(@RequestBody Request request){
        log.info("Waiting For Response From PAYMENT SERVICE PROVIDER");
        if (request.getAccount() == null || request.getAmount() <= 0 || request.getCustomerName() == null) {
            log.info("Couldn't Proceed Payments.");
            mailSender.sendEmail(request.getAccount().getCustomer().getEmail(),"Payment Failed ", "insufficient Balance");

            return new Response(Status.FAIL, request.getAmount(),new Date());

        }
        log.info("Payment Approved ");
        mailSender.sendEmail(request.getAccount().getCustomer().getEmail(),"Payment Approved ", String.valueOf(request.getAmount()));
        return new Response(Status.SUCCESS,request.getAmount(), new Date());

    }

}
