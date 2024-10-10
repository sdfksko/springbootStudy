package com.example.firstproject.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // REST API 처리하는 컨트롤러로 등록
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!";
    }
}
