package com.example.payments.services;

import com.example.payments.mail.Mail;
import com.example.payments.repositories.CustomerRepository;
import com.models.demo.models.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class PaymentService {
    private final CustomerRepository customerRepository;
    private final Mail mailSender;
    @Autowired
    public PaymentService(CustomerRepository customerRepository,Mail mail){
        this.customerRepository=customerRepository;
        this.mailSender=mail;
    }

    public Response processPayment(Request request){
        log.info("Waiting For Response From PAYMENT SERVICE PROVIDER");
        long id=request.getAccount().getCustomer().getId();
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getBalance() < request.getAmount()) {
                log.info("Couldn't Proceed Payments due to insufficient balance.");
                mailSender.sendEmail(customer.getEmail(), "Payment Failed", "Insufficient Balance");
                return new Response(Status.FAIL, request.getAmount(), new Date());
            }

            log.info("Payment Approved");
            mailSender.sendEmail(customer.getEmail(), "Payment Approved", String.valueOf(request.getAmount()));
            customer.setBalance(customer.getBalance() - request.getAmount());
            customerRepository.save(customer);

            return new Response(Status.SUCCESS, request.getAmount(), new Date());
        } else {
            log.error("Customer not found");
            throw new IllegalArgumentException("Customer not found");
        }
    }

}
