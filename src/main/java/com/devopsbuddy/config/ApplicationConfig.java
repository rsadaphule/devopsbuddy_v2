package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by sadap on 3/20/2017.
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.devopsbuddy.backend.persistence.respositories")
@EntityScan(basePackages = "com.devopsbuddy.backend.persistence.domain.backend")
@EnableTransactionManagement
@PropertySource("file:///${user.home}/devopsbuddy_config/application-common.properties")
public class ApplicationConfig {



}
