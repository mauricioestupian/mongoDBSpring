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
                System.out.println("================================");
                System.out.println("URI: " + request.getRequestURI());
                System.out.println("METHOD: " + request.getMethod());
                System.out.println("AUTH: " + request.getHeader("Authorization"));
                System.out.println("================================");

                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                        System.out.println("No se encontró el header de autorización o no tiene formato Bearer");
                        filterChain.doFilter(request, response);
                        return;
                }

                System.out.println("Header Recibido: " + authHeader);

                // Extrae el token sin la palabra 'Bearer '
                String token = authHeader.substring(7);
                System.out.println("Token Recibido: " + token);

                /*
                 * String username = jwtService.extraerUsuario(token);
                 * System.out.println("Usuario del Token: " + username);
                 */

                String username;

                try {

                        username = jwtService.extraerUsuario(token);

                        System.out.println("Usuario del Token: " + username);

                } catch (Exception e) {

                        System.out.println("Token JWT inválido o expirado");

                        filterChain.doFilter(request, response);
                        return;
                }

                // Cargar el usuario desde la base de datos
                UserDetails userDetails = detallesUsuarioService.loadUserByUsername(username);
                System.out.println("Usuario cargado desde BD: " + userDetails.getUsername());

                // Mostrar roles en consola para verificar
                System.out.println("Roles del Token:");
                userDetails.getAuthorities().forEach(auth -> System.out.println("- " + auth.getAuthority()));

                // Crear el token de autenticación para Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                // Guardar la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);

                // Continuar con la ejecución de los demás filtros
                filterChain.doFilter(request, response);
        }
}
