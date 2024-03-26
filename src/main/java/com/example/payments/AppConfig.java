package com.example.payments;

import com.example.payments.mail.MailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
@Bean
    public MailSender mailSender(){
    return new MailSender();
}
}
