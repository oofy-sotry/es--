package com.example.Elasticsearchcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.ElasticsearchAPI", "com.example.ElasticsearchItemDTO"})
public class ElasticsearchApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApiApplication.class, args);
    }
}
