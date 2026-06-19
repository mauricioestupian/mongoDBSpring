package com.example.demo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.enums.EstadoCita;

@Document(collection = "citas")
public class Cita {

    @Id
    private String id;

    private String codigo;

    private String mascotaId;

    private String usuarioId;

    private String servicioId;

    private LocalDate fecha;

    private LocalTime hora;

    private EstadoCita estado;

    private String observaciones;
}
