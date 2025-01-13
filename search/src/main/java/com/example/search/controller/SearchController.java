package com.example.search.controller;

import com.example.search.GeneralResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RestController
public class SearchController {


    @Autowired
    private RestTemplate restTemplate;

    public String fallback() {
        return "Fallback response for details";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        CompletableFuture<String> bookFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:8100/spring-boot-universities-api/api/universities/", String.class));


        CompletableFuture<String> detailsFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:8100/details/port", String.class));

        CompletableFuture.allOf(bookFuture, detailsFuture).join();

        String combinedData = "Universities: " + bookFuture.join() +
                ", Details: " + detailsFuture.join();

        GeneralResponse response = new GeneralResponse("200", LocalDateTime.now(), combinedData);
        return ResponseEntity.ok(response);
    }
}
