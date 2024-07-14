package com.diegoborbadev.expensemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ExpenseManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagerApplication.class, args);
    }
}