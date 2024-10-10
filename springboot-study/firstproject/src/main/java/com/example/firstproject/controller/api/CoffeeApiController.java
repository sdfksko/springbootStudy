//package com.example.firstproject.controller.api;
//
//import com.example.firstproject.dto.CoffeeForm;
//import com.example.firstproject.entity.Coffee;
//import com.example.firstproject.service.CoffeeService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//public class CoffeeApiController {
//    @Autowired
//    private CoffeeService coffeeService;
//
//    @GetMapping("/api/coffees")
//    public List<Coffee> index() {
//        return coffeeService.index();
//    }
//
//    @GetMapping("/api/coffees/{id}")
//    public Coffee show(@PathVariable Long id) {
//        return coffeeService.show(id);
//    }
//
//    @PostMapping("/api/coffees")
//    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm form) {
//        Coffee created = coffeeService.create(form);
//        return created != null
//                ? ResponseEntity.status(HttpStatus.OK).body(created)
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    @PatchMapping("/api/coffees/{id}")
//    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm form) {
//        Coffee updated = coffeeService.update(id, form);
//        return updated != null
//                ? ResponseEntity.status(HttpStatus.OK).body(updated)
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    @DeleteMapping("/api/coffees/{id}")
//    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
//        Coffee deleted = coffeeService.delete(id);
//        return deleted != null
//                ? ResponseEntity.status(HttpStatus.OK).body(deleted)
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    @PostMapping("/api/transaction-coffee")
//    public ResponseEntity<List<Coffee>> transactionTest(
//            @RequestBody List<CoffeeForm> dtos) {
//        List<Coffee> createdList = coffeeService.createCoffees(dtos);
//
//        return createdList != null
//                ? ResponseEntity.status(HttpStatus.OK).body(createdList)
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
////    @Autowired
////    private CoffeeRepository coffeeRepository;
////
////    @GetMapping("/api/coffees")
////    public List<Coffee> index() {
////        return coffeeRepository.findAll();
////    }
////
////    @GetMapping("/api/coffees/{id}")
////    public Coffee show(@PathVariable Long id) {
////        return coffeeRepository.findById(id).orElse(null);
////    }
////
////    @PostMapping("/api/coffees")
////    public Coffee create(@RequestBody CoffeeForm form) {
////        Coffee coffeeEntity = form.toEntity();
////        return coffeeRepository.save(coffeeEntity);
////    }
////
////    @PatchMapping("/api/coffees/{id}")
////    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm form) {
////        Coffee coffeeEntity = form.toEntity();
////        Coffee target = coffeeRepository.findById(id).orElse(null);
////        if(target == null || id != coffeeEntity.getId()) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
////        }
////        target.patch(coffeeEntity);
////        Coffee updated = coffeeRepository.save(target);
////        return ResponseEntity.status(HttpStatus.OK).body(updated);
////    }
////
////    @DeleteMapping("/api/coffees/{id}")
////    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
////        Coffee target = coffeeRepository.findById(id).orElse(null);
////        if(target == null) {
////            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
////        }
////        coffeeRepository.delete(target);
////        return ResponseEntity.status(HttpStatus.OK).body(target);
////    }
//}
