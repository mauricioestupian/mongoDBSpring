package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Recurso no encontrado");
        response.put("path", request.getRequestURI());
        response.put("errors", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /// Manejo de validaciones de campos en DTOs
    // Define el código HTTP de respuesta. 400 BAD REQUEST significa:
    // el cliente envió datos incorrectos.
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    // Cuando haya errores de validación como @NotNull, @Size(min = 3), usa este
    // método
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> manejarValidaciones(
            MethodArgumentNotValidException ex, // Contiene todos los errores de validación
            HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();// genera un mapa para almacenar los errores de validación

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
            // getBindingResult() -> contiene resultados de validación.
            // getFieldErrors() -> devuelve una lista de errores de validación para cada
            // campo.
        });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now()); // Momento del error "Fecha".
        response.put("status", 400); // Código HTTP.
        response.put("error", "Validacion de campos desde el backend"); // Tipo de error.
        response.put("path", request.getRequestURI()); // Endpoint donde ocurrió el error.
        response.put("errors", errores);// Mapa con los errores de validación (campo -> mensaje de error).

        return response;
    }
}