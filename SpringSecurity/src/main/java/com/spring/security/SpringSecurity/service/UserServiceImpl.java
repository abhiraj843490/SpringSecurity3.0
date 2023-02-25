package com.spring.security.SpringSecurity.service;
import com.spring.security.SpringSecurity.entity.UserEntity;
import com.spring.security.SpringSecurity.dto.Product;
import com.spring.security.SpringSecurity.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    List<Product>productList = null;
    @PostConstruct
    public void loadProductsFromDB(){
        productList = IntStream.rangeClosed(1,100)
                .mapToObj(i->Product.builder().productId(i)
                        .name("product "+i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<Product> getUsers() {
        return productList;
    }

    @Override
    public Product getUser(int id) {
        return productList.stream()
                .filter(product -> product.getProductId()==id)
                .findAny()
                .orElseThrow(()->new RuntimeException("product "+id+" not found" ));
    }
    public String addUser(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        return "user added successfully";
    }

}
