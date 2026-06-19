package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.auth.LoginRequestDto;
import com.example.demo.dto.auth.LoginResponseDto;
import com.example.demo.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

        private final AuthService authService;

        public AuthController(AuthService authService) {
                this.authService = authService;
        }

        @PostMapping("/login")
        public ResponseEntity<LoginResponseDto> login(
                        @RequestBody LoginRequestDto dto) {// ReponseEntity<LoginResponseDto> para devolver un objeto
                                                           // con datos del usuario y token JWT

                return ResponseEntity.ok(
                                authService.login(dto));// LoginResponseDto con token JWT y datos del usuario
                // el authService se encarga de validar las credenciales, generar el token y
                // devolver los datos del usuario
        }
}