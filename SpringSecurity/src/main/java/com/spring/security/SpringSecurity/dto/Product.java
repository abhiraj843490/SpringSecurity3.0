package com.spring.security.SpringSecurity.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int productId;
    private String name;
    private int qty;
    private double price;

}
