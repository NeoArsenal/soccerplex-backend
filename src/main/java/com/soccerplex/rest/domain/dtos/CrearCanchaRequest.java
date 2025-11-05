package com.soccerplex.rest.domain.dtos;

import lombok.Data;
/**
 * Patrón aplicado: DTO (Data Transfer Object)
 * Rol: Transportar datos entre capas sin exponer las entidades internas.
 * Justificación: Reduce acoplamiento y protege el modelo de dominio.
 */
@Data
public class CrearCanchaRequest {
    private String nombre;
    private String tipo;
    private Double precioPorHora; // Usando Double como pediste
    private String estado; // (Ej: "DISPONIBLE", "MANTENIMIENTO")
}
