package com.example.userapidemo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    public String helloWorld() {
        return "Hello World";
    }
}
