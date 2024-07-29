package com.diegoborbadev.expensemanager.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    // ModelMapper singleton
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        return MODEL_MAPPER;
    }
}