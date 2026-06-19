package com.example.demo.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "historiales")
public class HistorialClinico {

    @Id
    private String id;

    private String mascotaId;

    private LocalDate fecha;

    private String diagnostico;

    private String tratamiento;

    private String observaciones;
}