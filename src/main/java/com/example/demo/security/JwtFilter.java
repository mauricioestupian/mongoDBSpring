package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final DetallesUsuarioService detallesUsuarioService;

    public JwtFilter(JwtService jwtService, DetallesUsuarioService detallesUsuarioService) {
        this.jwtService = jwtService;
        this.detallesUsuarioService = detallesUsuarioService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            System.out.println("No se encontró el header de autorización o no tiene formato Bearer"); // Debug

            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("Header Recibido: "); // Debug
        System.out.println(authHeader); // Debug

        // token viene con formato "Bearer <token

        String token = authHeader.substring(7); // Extrae el token sin

        System.out.println("Token Recibido: "); // Debug
        System.out.println(token); // Debug

        String username = jwtService.extraerUsuario(token);

        System.out.println("Usuario del Token: "); // Debug
        System.out.println(username); // Debug

        System.out.println("Roles del Token: "); // Debug

        UserDetails userDetails = detallesUsuarioService.loadUserByUsername(username);

        userDetails.getAuthorities().forEach(auth -> System.out.println(auth.getAuthority())); // Debug

        UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext()
        .setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
