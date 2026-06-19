package com.example.demo.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.enums.SexoMascota;
import com.example.demo.enums.TipoMascota;

@Document(collection = "mascotas")
public class Mascota {

    @Id
    private String id;

    private Long codigo;

    private String usuarioId;

    private String nombre;

    private TipoMascota tipo;

    private String raza;

    private Integer edad;

    private Double peso;

    private SexoMascota sexo;

    private LocalDate fechaNacimiento;

    private String foto;
}
