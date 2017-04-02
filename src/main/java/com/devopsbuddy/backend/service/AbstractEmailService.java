package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by sadap on 3/16/2017.
 */
@PropertySource("file:///${user.home}/devopsbuddy_config/application-common.properties")
public abstract class AbstractEmailService implements EmailService{

   @Value("${default.to.address}")
   private String defaultToAddress;


    protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedback) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(defaultToAddress);
            message.setFrom(feedback.getEmail());
            message.setSubject("[DevOps Buddy]: Feedback received from " + feedback.getFirstName() + " "  + feedback.getLastName() + " !" );
            message.setText(feedback.getFeedback());

        return message;
    }




    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
    }
}
