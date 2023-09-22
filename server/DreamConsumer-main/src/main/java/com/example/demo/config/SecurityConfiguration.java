package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // clickJacking 막고자 frameOption() 디폴트 DENY로 활성화되어 있음. 동일 출처 request만 페이지 렌더링 허용
                .and()
                .csrf().disable()
                .formLogin()
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/users/sign-up").permitAll()
                        .antMatchers("/users/{user-id}").hasRole("USER")
                        .antMatchers("/activate/{user-id}").hasRole("USER")
                        .antMatchers("/**").permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
