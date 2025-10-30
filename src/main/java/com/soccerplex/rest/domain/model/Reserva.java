package com.soccerplex.rest.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class Reserva {
    private Long id;
    private String codigo;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double precio;
    private String estado;
    private Cliente cliente; // Objeto de dominio
    private Cancha cancha;   // Objeto de dominio
}