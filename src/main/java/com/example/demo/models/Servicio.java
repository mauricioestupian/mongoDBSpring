package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "servicios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servicio {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id
    private String id; // ID único generado por MongoDB
    private Long codigo; // código único del servicio auto-incremental
    private String nombre; // nombre del servicio
    private String descripcion; // descripción del servicio
    private Integer precio; // precio del servicio
    private Integer duracion; // duración del servicio en minutos(ejemplo: "30", "60", "90")
    private CategoriaServ categoria; // clase enum para categorizar el servicio
    private String imagen; // URL final (cloud o local)
}