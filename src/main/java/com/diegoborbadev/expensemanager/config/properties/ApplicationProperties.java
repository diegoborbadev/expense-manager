package com.diegoborbadev.expensemanager.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.info")
public record ApplicationProperties(
        String name,
        String version,
        String javaVersion,
        String description,
        String encoding,
        String license,
        String licenseUrl
) {
}