package com.example.payments.services;

import com.example.payments.mail.MailSender;
import com.example.payments.repositories.BookRepository;
import com.example.payments.repositories.CustomerRepository;
import com.models.demo.models.entity.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class PaymentService {
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final MailSender mailSender;
    @Autowired
    public PaymentService(BookRepository bookRepository,CustomerRepository customerRepository,MailSender mailSender){
        this.bookRepository=bookRepository;
        this.customerRepository=customerRepository;
        this.mailSender=mailSender;
    }

    public Response processPayment(Request request){
        log.info("Waiting For Response From PAYMENT SERVICE PROVIDER");
        if (request.getAccount() == null || request.getAmount() <= 0 || request.getCustomerName() == null) {
            log.info("Couldn't Proceed Payments.");
            mailSender.sendEmail(request.getAccount().getCustomer().getEmail(),"Payment Failed ", "insufficient Balance");
            return new Response(Status.FAIL, request.getAmount(),new Date());
        }
        log.info("Payment Approved ");
        mailSender.sendEmail(request.getAccount().getCustomer().getEmail(),"Payment Approved ", String.valueOf(request.getAmount()));
        Customer customer = customerRepository.findById((long) request.getAccount().getCustomer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + (long) request.getAccount().getCustomer().getId()));

        Book book = bookRepository.findById(request.getBook().getId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " +(long)request.getBook().getId()));

        customer.getBooks().add(book);
        book.getCustomers().add(customer);

        customerRepository.save(customer);
        bookRepository.save(book);

        return new Response(Status.SUCCESS,request.getAmount(), new Date());
    }
}
