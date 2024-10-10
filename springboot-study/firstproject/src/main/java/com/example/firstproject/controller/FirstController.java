package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 브라우저에서 url요청시에 들어오게끔 해주는 어노테이션
public class FirstController {
    @GetMapping("/eng")    // 브라우저에서 "/hi"로 요청이 들어오면 아래 메소드가 실행됨
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "HongPark~ Nice to meet you");
        return "greetings"; // templates/greetings.mustache로 이동
    }

    @GetMapping("/kor")    // 브라우저에서 "/"로 요청이 들어오면 아래 메소드가 실행됨
    public String niceToMeetYou2(Model model) {
        model.addAttribute("username", "홍팍님 반갑습니다.");
        return "greetings"; // templates/greetings.mustache로 이동
    }

    @GetMapping("/hi")    // 브라우저에서 "/"로 요청이 들어오면 아래 메소드가 실행됨
    public String niceToMeetYou3(Model model) {
        model.addAttribute("username", "홍팍");
        return "greetings"; // templates/greetings.mustache로 이동
    }

    @GetMapping("/test1")    // 브라우저에서 "/"로 요청이 들어오면 아래 메소드가 실행됨
    public String test() {
        return "test1"; // templates/test1.mustache로 이동
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }
}
