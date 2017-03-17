package com.devopsbuddy.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by sadap on 3/16/2017.
 */
public class MockEmailService extends AbstractEmailService{
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);


    @Override
    public void sendGenericEmailMessage(SimpleMailMessage message) {
        LOG.debug("simulating email service");
        LOG.info(message.toString());
    }
}
