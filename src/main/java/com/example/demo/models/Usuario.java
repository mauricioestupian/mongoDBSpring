package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "UsuariosPerfil")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    private String id; // ID único para cada usuario el mismo del autentificación
    @NotBlank
    private String nom; // nombre del usuario
    @NotNull
    private String ape; // apellido del usuario
    @NotNull
    private Documento doc; // documento de identidad del usuario objeto con tipo y número
    private Direccion dir; // dirección del usuario objeto con calle, numero, ciudad, y código postal
    // private List<Direccion> dire; // lista de direcciones
    @NotBlank
    private String email; // correo electrónico del usuario

}
