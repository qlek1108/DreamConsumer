package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // clickJacking 막고자 frameOption() 디폴트 DENY로 활성화되어 있음. 동일 출처 request만 페이지 렌더링 허용
                .and()
                .csrf().disable()
                //.cors(Customizer.withDefaults()) // CorsConfigurationSource Bean 사용
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .formLogin().disable() // form Login은 SSR에서 사용
                .httpBasic().disable() // httpBasic : UserName/Password 정보를 http header에 실어 인증
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PATCH", "DELETE", "OPTIONS"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin",
                "Access-Control-Allow-Methods",
                "Access-Control-Allow-Headers"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Accept", "Content-Type"));
        corsConfiguration.setAllowCredentials(true);


        //  CorsConfigurationSource 인터페이스의 구현 클래스 UrlBasedCorsConfigurationSource
        //  source에 configuration 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}