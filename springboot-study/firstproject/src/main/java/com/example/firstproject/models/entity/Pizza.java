//package com.example.firstproject.entity;
//
//import com.example.firstproject.dto.PizzaDto;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
//public class Pizza {
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column
//    private String name;
//    @Column
//    private String price;
//
//    public static Pizza createPizza(PizzaDto pizzaDto) {
//        return new Pizza(
//                null,
//                pizzaDto.getName(),
//                pizzaDto.getPrice()
//        );
//    }
//
//    public void patch(Pizza pizza) {
//        if(pizza.name != null) {
//            this.name = pizza.name;
//        }
//        if(pizza.price != null) {
//            this.price = pizza.price;
//        }
//    }
//}
