package com.example.demo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.enums.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "UsuariosAuth")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioAuth {
    @Id
    private String id; // ID único para cada usuario el mismo del perfil
    private String user; // dato de usuario para autenticación
    private String pass; // dato de contraseña para autenticación encriptada(bcrypt)
    // private List<String> roles; // lista de roles para autorización (ejemplo:
    // "ROLE_USER", "ROLE_ADMIN")
    private List<Rol> roles; // lista de roles usando enum

}
