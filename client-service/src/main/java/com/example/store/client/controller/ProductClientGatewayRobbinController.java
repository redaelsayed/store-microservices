package com.example.store.client.controller;

import com.example.store.client.service.ProductClientService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.config.IClientConfig;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductClientGatewayRobbinController {

    @Autowired
    @Qualifier("restTemplateRobbin")
    private RestTemplate restTemplate;

    @Autowired
    private ProductClientService productClientService;

    /**
     * Client Load balancing is done by the eureka client "getNextServerFromEureka" for the gateway. The gateway is responsible for the load balancing for product service.
     * @return
     */
    @GetMapping(path = "/client/product/gateway/ribbon")
    public String getProduct(HttpServletRequest request) {
        return productClientService.getProduct(restTemplate, "http://gateway/product/product");
    }
}