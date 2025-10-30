package com.soccerplex.rest.domain.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class CrearReservaRequest {
    private Long clienteId;
    private Long canchaId;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double precio; 
}