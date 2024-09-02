package com.aduca.lms.service;

import com.aduca.lms.domain.EmailSetting;
import com.aduca.lms.repository.EmailSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

  private  JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }



  public void sendOrderConfirmationEmail(String to, String courseTitle) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject("Course Purchase Confirmation");
    message.setText("Dear User,\n\nThank you for purchasing the course: " + courseTitle + ".\n\nEnjoy your learning journey!\n\nBest Regards,\nAduca");
    mailSender.send(message);
  }
}

