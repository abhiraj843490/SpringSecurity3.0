package com.spring.security.SpringSecurity.controller;

import com.spring.security.SpringSecurity.entity.UserEntity;
import com.spring.security.SpringSecurity.dto.Product;
import com.spring.security.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class RegistrationController {
    @Autowired
    private UserService service;
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome this endpoint is not secure";
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllUser(){
        return service.getUsers();
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserEntity userEntity){
        return service.addUser(userEntity);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getUserById(@PathVariable int id){
        return service.getUser(id);
    }

}
