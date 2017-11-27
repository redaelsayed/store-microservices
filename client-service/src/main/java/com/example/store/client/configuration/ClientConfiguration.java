package com.example.store.client.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfiguration {

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

    @LoadBalanced
    @Bean(name="restTemplateRobbin")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}