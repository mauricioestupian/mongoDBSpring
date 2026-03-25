package com.example.demo.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
// esto hace que la clase sea de la configuracion del sistema
// se cargan beans atutomaticamente y configutaciones del CORS.
public class SecurityConfig {

    @Bean
    // el autentication manager automaticamente hace uso e DetallesUsuarioService y
    // PasswordEncoder
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    // se definen las reglas de seguridad
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http // Activa CORS en Spring Security esto usa automáticamente:
             // CorsConfigurationSource
                .cors(cors -> {
                })
                .csrf(csrf -> csrf.disable())// Desactiva protección CSRF
                .authorizeHttpRequests(auth -> auth // define quién puede acceder a qué
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()// Permite todas las peticiones OPTIONS
                        // El navegador hace esto antes de un POST:
                        // OPTIONS /auth/login
                        // Si lo bloquemos → CORS falla
                        .requestMatchers("/auth/**", "/api/usuarios/registrar").permitAll()// rutas publicas
                        .anyRequest().authenticated());
        // Cualquier otra ruta:requiere token JWT

        return http.build();
        // Aplica toda la configuración
    }

    @Bean
    // Define reglas de acceso desde frontend
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));// Solo permite peticiones desde:
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));// Permite estos métodos HTTP
        config.setAllowedHeaders(List.of("*"));// Permite cualquier header
        config.setAllowCredentials(true);
        // Permite: cookies, headers de autenticación

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);// Aplica CORS a TODAS las rutas

        return source;
    }
}

/*
 * Resumen
 * Frontend (React)
 * ↓
 * CORS Config ✔
 * ↓
 * Security FilterChain
 * ↓
 * ¿Ruta pública?
 * ✔ sí → entra
 * no → pide JWT
 */