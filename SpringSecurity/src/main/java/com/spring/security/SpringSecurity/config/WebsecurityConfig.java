package com.spring.security.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebsecurityConfig {
    @Bean
    //authentication
                                                //PasswordEncoder passwordEncoder
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("abhiraj")
//                .password(passwordEncoder.encode("abhiraj"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("jhon")
//                .password(passwordEncoder.encode("jhon"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
        //create own UserDetailsService to interact with DB
        return new UserInfoUserDetailsService();
    }
    //authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/product/welcome","/product/new")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/product/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //all info u need to give to the the aAuthenticationProvider
    //who is the userDetail service and what is the password encoder
    //these 2 info if u can give to the AuthenticationProvider,
    //AuthenticationProvider will talk to the user details and generate
    //the user details object and it will set to the authentication obj
    //that is what the internal flow
    //authenticationProvider talk to
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
