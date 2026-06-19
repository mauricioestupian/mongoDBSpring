package com.example.demo.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vacunas")
public class Vacuna {

    @Id
    private String id;

    private String mascotaId;

    private String nombre;

    private LocalDate fechaAplicacion;

    private LocalDate proximaDosis;
}
