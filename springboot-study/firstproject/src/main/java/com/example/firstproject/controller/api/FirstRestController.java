package com.example.firstproject.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
    @GetMapping("/helloworld")
    public String hello() {
        return"Hello World!";
    }
}
