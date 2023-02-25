package com.spring.security.SpringSecurity.service;

import com.spring.security.SpringSecurity.entity.UserEntity;
import com.spring.security.SpringSecurity.dto.Product;

import java.util.List;

public interface UserService {

    List<Product> getUsers();

    Product getUser(int id);

    String addUser(UserEntity userEntity);
}
