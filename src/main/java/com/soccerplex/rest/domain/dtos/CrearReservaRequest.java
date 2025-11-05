package com.soccerplex.rest.domain.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;
/**
 * Patrón aplicado: DTO (Data Transfer Object)
 * Rol: Transportar datos entre capas sin exponer las entidades internas.
 * Justificación: Reduce acoplamiento y protege el modelo de dominio.
 */
@Data
public class CrearReservaRequest {
    private Long clienteId;
    private Long canchaId;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double precio; 
}
