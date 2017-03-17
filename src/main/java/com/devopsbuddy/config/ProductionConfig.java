package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by sadap on 3/16/2017.
 */
@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/devopsbuddy_config/application-prod.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService()
    {
        return new SmtpEmailService();
    }

}
