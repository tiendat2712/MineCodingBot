package com.johnley.begin_spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringController {
    @GetMapping("/hello")
    String message() {
        return "Hello!! John is here to win the spring project!";
    }
}
