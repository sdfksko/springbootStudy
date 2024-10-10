package com.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestJspController {
    @GetMapping("/jsp/test1")
    public String index() {
        // 1. templates/index1.mustache로 이동을 혹은
        // 2. webapp/WEB-INF/views/index1.jsp로 이동을 함
        return "index1";
    }

    @GetMapping("/jsp/test2")
    public String index2() {
        // 1. templates/index2.mustache로 이동을 혹은
        // 2. webapp/WEB-INF/views/index2.jsp로 이동을 함
        return "test1/index2";
    }
}
