package com.banking.cards.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "topics")
@Data
public class Topics {

    private String applicationSubmittedEvent;
    private String applicationValidatedEvent;

}
