package com.example.secondproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SPAPageController {
    @GetMapping("/page/main")
    public ModelAndView pageMain() {
        return new ModelAndView("main");
    }

    @GetMapping("/page/sub")
    public ModelAndView pageSub() {
        return new ModelAndView("sub");
    }
}
