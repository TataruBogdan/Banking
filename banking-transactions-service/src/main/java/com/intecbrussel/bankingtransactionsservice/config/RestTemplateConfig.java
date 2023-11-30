package com.intecbrussel.bankingtransactionsservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /*Spring will provide the configured instance with the HttpComponentsClientHttpRequestFactory.
    This can be especially useful in scenarios where you need fine-grained control over your HTTP requests or when
    you want to take advantage of specific features provided by the Apache HttpClient library.*/
    @Bean
    public RestTemplate createRestTemplate(RestTemplateBuilder builder){
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);

        RestTemplate restTemplate = builder.build();
        restTemplate.setRequestFactory(requestFactory);

        return restTemplate;
        //return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
}
