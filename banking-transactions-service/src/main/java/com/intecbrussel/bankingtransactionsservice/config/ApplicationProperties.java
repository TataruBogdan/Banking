package com.intecbrussel.bankingtransactionsservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rest-client")
@Data
public class ApplicationProperties {

    private String individualUrl;
    private String accountCurrentUrl;
    private String accountsDepositUrl;
    private String accountsLoanUrl;
    private String accountCurrentDebitUrl;
}
