package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.Servicio;

public interface ServicioRepository extends MongoRepository<Servicio, String> {

}
