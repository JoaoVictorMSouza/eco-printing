package com.ecoprinting.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecoprinting.app.service.interfaces.IAutenticacaoService;

@Configuration
@EnableWebSecurity
public class SegurancaConfig {

    @Autowired
    private IAutenticacaoService autenticacaoService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/resources/**", "/videos/**").permitAll()
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/usuario/criar").permitAll()
                .requestMatchers("/doacao/**").hasRole("ADMIN")
                .requestMatchers("/usuario/**").hasAnyRole("ADMIN", "COMUM")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .addHeaderWriter((request, response) -> {
                    response.setHeader("X-Frame-Options", "ALLOW-FROM http://localhost:8080"); // Permite frames de localhost
                })
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder senhaEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(autenticacaoService)
                .passwordEncoder(senhaEncoder())
                .and()
                .build();
    }
}
