package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    @Id
    private String id; // ID único para cada usuario el mismo del autentificación
    private String nom; // nombre del usuario

}
