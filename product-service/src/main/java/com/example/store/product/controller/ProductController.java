package com.example.store.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RefreshScope
@RestController
public class ProductController {

    @Value("${store.name}")
    private String storeName;

    @GetMapping(path = "/product")
    public String getProduct(HttpServletRequest request) {
        return "product from: " + storeName + " == " + request.getServerName() + ":" + request.getServerPort();
    }
}
