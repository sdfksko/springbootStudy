//package com.example.firstproject.controller.api;
//
//import com.example.firstproject.dto.PizzaDto;
//import com.example.firstproject.entity.Pizza;
//import com.example.firstproject.service.PizzaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.w3c.dom.EntityReference;
//
//import java.util.List;
//
//@RestController
//public class PizzaApiController {
//    @Autowired
//    private PizzaService pizzaService;
//
//    @GetMapping("/api/pizzas")      // 모든 피자 조회
//    public ResponseEntity<List<Pizza>> index() {
//        List<Pizza> pizzaList = pizzaService.index();
//        return ResponseEntity.status(HttpStatus.OK).body(pizzaList);
//
//    }
//
//    @GetMapping("/api/pizzas/{id}") // 특정 피자 조회
//    public ResponseEntity<Pizza> show(@PathVariable Long id) {
//        Pizza pizza = pizzaService.show(id);
//        return ResponseEntity.status(HttpStatus.OK).body(pizza);
//    }
//
//    @PostMapping("/api/pizzas")     // 피자 생성
//    public ResponseEntity<Pizza> create(@RequestBody PizzaDto pizzaDto) {
//        Pizza created = pizzaService.create(pizzaDto);
//        return ResponseEntity.status(HttpStatus.OK).body(created);
//    }
//
//    @PatchMapping("/api/pizzas/{id}")   // 피자 수정
//    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody PizzaDto pizzaDto) {
//        Pizza updated = pizzaService.update(id, pizzaDto);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    @DeleteMapping("/api/pizzas/{id}")  // 피자 삭제
//    public ResponseEntity<Pizza> delete(@PathVariable Long id) {
//        Pizza deleted = pizzaService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK).body(deleted);
//    }
//}
