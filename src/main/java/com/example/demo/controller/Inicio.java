package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Inicio {

    @GetMapping("/inicio")
    public Map<String, Object> prueba() {
        Map<String, Object> respuesta = new HashMap<>();

        // Agrega las propiedades que quieras que tenga tu JSON
        respuesta.put("status", "exitoso");
        respuesta.put("codigo", 200);
        respuesta.put("mensaje", "Esto es un JSON de prueba");
        return respuesta;
    }

}
