package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.models.Servicio;
import com.example.demo.repositories.ServicioRepository;

@Service
public class ServicioServiceImple implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImple(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public Servicio create(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

}
