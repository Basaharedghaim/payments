package com.example.payments;

import lombok.extern.slf4j.Slf4j;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/paymentsClient")
public class PaymentsController {
    @PostMapping("/buyBook")
    public Response processPayments(@RequestBody Request request){
        log.info("Waiting For Response From PAYMENT SERVICE PROVIDER");
        if (request.getAccount() == null || request.getAmount() <= 0 || request.getCustomerName() == null) {
            log.info("Couldn't Proceed Payments.");
           return new Response(Status.FAIL, request.getAmount(),new Date());

        }
        log.info("Payment Approved ");
        return new Response(Status.SUCCESS,request.getAmount(), new Date());


    }

}
