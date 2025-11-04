package com.racinger.librarySystem.Infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.racinger.librarySystem")
public class DatabaseFactory {
    // Database configuration class
}