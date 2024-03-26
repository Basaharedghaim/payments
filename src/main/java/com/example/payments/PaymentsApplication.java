package com.example.payments;

import com.example.payments.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.payments.mail"})

public class PaymentsApplication {
	public static void main(String[] args) {SpringApplication.run(PaymentsApplication.class, args);}

}
