//package com.example.firstproject.service;
//
//import com.example.firstproject.dto.CoffeeForm;
//import com.example.firstproject.entity.Coffee;
//import com.example.firstproject.repository.CoffeeRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//public class CoffeeService {
//    @Autowired
//    private CoffeeRepository coffeeRepository;
//
//    public List<Coffee> index() {
//        return coffeeRepository.findAll();
//    }
//
//    public Coffee show(Long id) {
//        return coffeeRepository.findById(id).orElse(null);
//    }
//
//    public Coffee create(@RequestBody CoffeeForm form) {
//        Coffee coffeeEntity = form.toEntity();
//        if(coffeeEntity != null && coffeeEntity.getId() != null) {
//            return null;
//        }
//        return coffeeRepository.save(coffeeEntity);
//    }
//
//    public Coffee update(Long id, CoffeeForm form) {
//        Coffee coffeeEntity = form.toEntity();
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//        if(target == null || id != coffeeEntity.getId()) {
//            return null;
//        }
//        target.patch(coffeeEntity);
//        Coffee updated = coffeeRepository.save(target);
//        return updated;
//    }
//
//    public Coffee delete(Long id) {
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//        if (target == null) {
//            return null;
//        }
//        coffeeRepository.delete(target);
//        return target;
//    }
//
//    @Transactional
//    public List<Coffee> createCoffees(List<CoffeeForm> dtos) {
//        List<Coffee> coffeeList
//                = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
//        coffeeList.forEach(coffee -> coffeeRepository.save(coffee));
//
//        coffeeRepository.findById(-1L)
//                .orElseThrow(() -> new IllegalArgumentException("결제 실패"));
//
//        return coffeeList;
//    }
//}
