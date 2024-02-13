package com.coin.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
public class MailServerConfig {

    private String username;

    private String password;

    private String domain;

    private String url;

    private String from;
}