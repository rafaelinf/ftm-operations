package com.financial.transaction.operations.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfiguration {

    @Value("${http.timeout}")
    private Integer httpTimeout;	
	
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(httpTimeout))
                .setReadTimeout(Duration.ofSeconds(httpTimeout))
                .build();
    }	
	
}
