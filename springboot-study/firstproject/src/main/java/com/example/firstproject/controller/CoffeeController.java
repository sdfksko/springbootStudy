//package com.example.firstproject.controller;
//
//import com.example.firstproject.entity.Coffee;
//import com.example.firstproject.repository.CoffeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class CoffeeController {
//    @Autowired
//    private CoffeeRepository coffeeRepository;
//
//    @GetMapping("/coffees")
//    public String index(Model model) {
//        List<Coffee> coffeeList = (List<Coffee>) coffeeRepository.findAll();
//        model.addAttribute("coffeeList", coffeeList);
//        return "coffees/index";
//    }
//}
