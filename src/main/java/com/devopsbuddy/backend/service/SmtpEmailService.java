package com.devopsbuddy.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by sadap on 3/16/2017.
 */
public class SmtpEmailService extends AbstractEmailService{
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);


    @Autowired
    private MailSender mailSender;


    @Override
    public void sendGenericEmailMessage(SimpleMailMessage message) {
      LOG.debug("sending email for: {} " + message);
      mailSender.send(message);
      LOG.info("email sent");
    }
}
