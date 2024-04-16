package com.example.payments.services;

import com.example.payments.exceptions.InsufficentBalanceException;
import com.example.payments.mail.Mail;
import com.example.payments.repositories.CustomerRepository;
import com.models.demo.models.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public String processPayment(Request request) {
        log.info("Waiting For Response From PAYMENT SERVICE PROVIDER");
        long id = request.getAccount().getCustomer().getId();
        Optional<Customer> customerOptional = customerRepository.findById(id);
                Customer customer = customerOptional.get();
                if (customer.getBalance() > request.getAmount()) {
                    System.out.println(customer.getBalance());
                    log.info("Payment Approved");
                    mailSender.sendEmail(customer.getEmail(), "Payment Approved", String.valueOf(request.getAmount()));


                    customer.setBalance(customer.getBalance() - request.getAmount());
                    customerRepository.save(customer);
                    return "Success";
                } else
                {
                    log.info("Couldn't Proceed Payments due to insufficient balance.");
                    mailSender.sendEmail(customer.getEmail(), "Payment Failed", "Insufficient Balance");
                   throw new InsufficentBalanceException("\"Couldn't Proceed Payments due to insufficient balance to Customer."
                           +customer.getFirstName()+" "+customer.getLastName()+
                           " Current Balance:" +customer.getBalance());
                }


    }

}


