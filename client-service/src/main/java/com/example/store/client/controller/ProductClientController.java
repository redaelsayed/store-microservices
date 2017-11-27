package com.example.store.client.controller;

import com.example.store.client.service.ProductClientService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Path;

@RestController
public class ProductClientController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private ProductClientService productClientService;

    /**
     * Client Load balancing is done by the eureka client "getNextServerFromEureka" for the product service (without gateway).
     * @return
     */
    @GetMapping(path = "/client/product")
    public String getProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo info = eurekaClient.getNextServerFromEureka("product", false);
        String baseUrl = info.getHomePageUrl();
        return productClientService.getProduct(restTemplate, baseUrl + "/product");
    }
}
