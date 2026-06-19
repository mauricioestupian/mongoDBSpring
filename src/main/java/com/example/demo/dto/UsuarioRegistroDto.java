package com.example.demo.dto;

import java.util.List;

import com.example.demo.enums.Rol;
import com.example.demo.models.Direccion;
import com.example.demo.models.Documento;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRegistroDto {
    @NotBlank(message = "El nombre es obligatorio dese backend")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio tambien dese backend    ")
    private String apellido;
    private Documento documento;
    private Direccion direccion;
    private String email;
    private String usuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private List<Rol> roles;
}
