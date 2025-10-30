package com.soccerplex.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para peticiones POST desde Postman o Angular
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // Permite libre acceso a tus endpoints API
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.disable()) // Desactiva formulario de login
            .httpBasic(basic -> basic.disable()); // Desactiva login básico

        return http.build();
    }
    // --- 3. ¡AÑADE ESTE BEAN FALTANTE! ---
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Esto le dice a Spring qué implementación usar 
        // cuando ClienteService pida un "PasswordEncoder"
        return new BCryptPasswordEncoder();
    }
}
