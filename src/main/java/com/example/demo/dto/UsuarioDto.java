package com.example.demo.dto;

import com.example.demo.models.Direccion;
import com.example.demo.models.Documento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private String id; // ID único para cada usuario el mismo del autentificación
    private String nombre; // nombre del usuario
    private String apellido; // apellido del usuario
    private Documento documento; // documento de identidad del usuario objeto con tipo y número
    private Direccion direccion; // dirección del usuario objeto con calle, numero, ciudad, y código postal
    // private List<Direccion> direcciones; // lista de direcciones

    private String email; // correo electrónico del usuario
    private String telefono; // número de teléfono del usuario

}
