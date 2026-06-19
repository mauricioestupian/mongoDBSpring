package com.example.demo.services;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.demo.dto.auth.LoginRequestDto;
import com.example.demo.dto.auth.LoginResponseDto;
import com.example.demo.models.Usuario;
import com.example.demo.models.UsuarioAuth;
import com.example.demo.repositories.UsuarioAuthRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.security.JwtService;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final UsuarioAuthRepository usuarioAuthRepo;
    private final UsuarioRepository usuarioRepo;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authManager,
            UsuarioAuthRepository usuarioAuthRepo,
            UsuarioRepository usuarioRepo,
            JwtService jwtService) {

        this.authManager = authManager;
        this.usuarioAuthRepo = usuarioAuthRepo;
        this.usuarioRepo = usuarioRepo;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto dto) {

        // 1. Validar usuario y contraseña
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsuario(),
                        dto.getPassword()));

        // 2. Obtener datos de autenticación
        UsuarioAuth usuarioAuth = usuarioAuthRepo
                .findByUser(dto.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Obtener perfil
        Usuario perfil = usuarioRepo
                .findById(usuarioAuth.getId())
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        // 4. Roles enum -> String
        List<String> roles = usuarioAuth.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

        // 5. Generar JWT
        String token = jwtService.generarToken(
                usuarioAuth.getUser(),
                roles,
                perfil.getNom(),
                perfil.getApe());

        // 6. Construir respuesta
        return LoginResponseDto.builder()
                .id(usuarioAuth.getId())
                .usuario(usuarioAuth.getUser())
                .nombreCompleto(
                        perfil.getNom() + " " + perfil.getApe())
                .roles(roles)
                .token(token)
                .build();
    }
}