package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.enums.CategoriaServicio;
import com.example.demo.models.Servicio;
import com.example.demo.services.ServicioService;

@RestController
@RequestMapping("/api/servicios")
public class ServicioControler {

    private final ServicioService servicioService;

    public ServicioControler(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // Obtener todas las categorías disponibles (enum CategoriaServ)
    @GetMapping("/categorias")
    public ResponseEntity<CategoriaServicio[]> obtenerCategorias() {
        return ResponseEntity.ok(CategoriaServicio.values());
    }

    // Crear un nuevo servicio
    @PostMapping("/crear")
    public ResponseEntity<Servicio> creaServicio(@RequestBody Servicio servicio) {
        Servicio creado = servicioService.create(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

}
