//package com.example.firstproject.service;
//
//
//import com.example.firstproject.dto.PizzaDto;
//import com.example.firstproject.entity.Pizza;
//import com.example.firstproject.repository.PizzaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PizzaService {
//    @Autowired
//    private PizzaRepository pizzaRepository;
//
//    // 모든 피자 조회
//    public List<Pizza> index() {
//        List<Pizza> pizzaList = pizzaRepository.findAll();
//        return pizzaList;
//    }
//
//    // 특정 피자 조회
//    public Pizza show(Long id) {
//        Pizza pizza = pizzaRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 피자입니다.")
//        );
//        return pizza;
//    }
//
//    // 피자 생성
//    public Pizza create(PizzaDto pizzaDto) {
//        Pizza pizza = Pizza.createPizza(pizzaDto);
//        Pizza created = pizzaRepository.save(pizza);
//        return created;
//    }
//
//    // 피자 수정
//    public Pizza update(Long id, PizzaDto pizzaDto) {
//        Pizza target = pizzaRepository.findById(id).orElseThrow(
//            () -> new IllegalArgumentException("존재하지 않는 피자입니다.")
//        );
//        Pizza pizza = Pizza.createPizza(pizzaDto);
//        target.patch(pizza);
//        Pizza updated = pizzaRepository.save(target);
//
//        return updated;
//    }
//
//    // 피자 삭제
//    public Pizza delete(Long id) {
//        Pizza target = pizzaRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 피자입니다.")
//        );
//        pizzaRepository.delete(target);
//        return target;
//    }
//}
