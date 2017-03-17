package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by sadap on 3/16/2017.
 */
public interface EmailService {

    /***
     *
     * @param feedbackPojo
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    /***
     *
     * @param message
     */
    public void sendGenericEmailMessage(SimpleMailMessage message);




}
