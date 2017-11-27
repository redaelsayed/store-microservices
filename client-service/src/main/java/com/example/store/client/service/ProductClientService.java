package com.example.store.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClientService {

    @HystrixCommand(fallbackMethod = "getProductFallback")
    public String getProduct (RestTemplate restTemplate, String url) {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    public String getProductFallback (RestTemplate restTemplate, String url, Throwable t) {
        return "fallback! => " + t.getMessage();
    }
}
