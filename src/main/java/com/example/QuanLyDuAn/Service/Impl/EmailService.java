package com.example.QuanLyDuAn.Service.Impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpMessage(String to, String subject, String message) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");


        helper.setTo(to);

        helper.setSubject(subject);

        helper.setText(message, true);

        javaMailSender.send(msg);
    }

}