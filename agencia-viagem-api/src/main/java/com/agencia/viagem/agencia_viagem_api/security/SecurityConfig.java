package com.agencia.viagem.agencia_viagem_api.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}


@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
http
.csrf(csrf -> csrf.disable())
.authorizeHttpRequests(auth -> auth
.requestMatchers("/api/auth/**").permitAll()
.requestMatchers("/h2-console/**").permitAll()
.anyRequest().authenticated()
)
.httpBasic(Customizer.withDefaults());


http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));


return http.build();
}
}