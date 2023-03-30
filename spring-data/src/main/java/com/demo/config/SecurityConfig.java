package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user")
//                .password("{noop}userPass")
//                .roles("USER")
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password("{noop}adminPass")
//                .roles("USER", "ADMIN")
//                .build());
//        return manager;
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.DELETE)
                .hasRole("admin")
                .requestMatchers("/v1/api/cliente/**", "/v1/api/employee/**")
                .hasAnyRole("user", "admin")
                .requestMatchers("/v1/api/login/**")
                .anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
                //.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
        return http.build();
    }

}
