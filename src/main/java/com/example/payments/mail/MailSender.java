package com.example.payments.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Slf4j
@Component
public class MailSender {
@Autowired
  private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body){
    SimpleMailMessage message=new SimpleMailMessage();
    message.setFrom("basharedghaim@gmail.com");
    message.setTo(toEmail);
    message.setText(body);
    message.setSubject(subject);
    javaMailSender.send(message);
    log.info("Mail Sent Successfully .....");
}

}
