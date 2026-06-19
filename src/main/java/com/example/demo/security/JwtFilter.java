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

    public JwtFilter(
            JwtService jwtService,
            DetallesUsuarioService detallesUsuarioService) {

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

            System.out.println("No se recibió JWT");

            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("Header recibido:");
        System.out.println(authHeader);

        String token = authHeader.substring(7);

        System.out.println("Token:");
        System.out.println(token);

        String usuario = jwtService.extraerUsuario(token);

        System.out.println("Usuario del token:");
        System.out.println(usuario);

        // cargar roles del usuario y guardarlos en el contexto de seguridad
        UserDetails userDetails = detallesUsuarioService.loadUserByUsername(usuario);

        System.out.println("Usuario cargado desde BD:");
        System.out.println(userDetails.getUsername());

        System.out.println("Roles:");

        
        //autoridades del usuario (roles) para verificar que se cargaron correctamente
        userDetails.getAuthorities()
                .forEach(a -> System.out.println(a.getAuthority()));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext()
                .setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

}
