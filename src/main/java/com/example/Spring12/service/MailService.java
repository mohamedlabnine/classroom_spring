package com.example.Spring12.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    private  JavaMailSender javaMailSender ;

    public void sendMail(String to , String subject , String  body){

        SimpleMailMessage mailMessage = new SimpleMailMessage() ;
        mailMessage.setFrom("mohamedlabnine1@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }

}
