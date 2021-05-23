package com.kueblearn.user_service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.kueblearn.user_service.domain")
@EnableJpaRepositories("com.kueblearn.user_service.repos")
@EnableTransactionManagement
public class DomainConfig {
}
