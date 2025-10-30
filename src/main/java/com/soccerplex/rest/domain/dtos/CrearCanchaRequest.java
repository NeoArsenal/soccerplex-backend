package com.soccerplex.rest.domain.dtos;

import lombok.Data;

@Data
public class CrearCanchaRequest {
    private String nombre;
    private String tipo;
    private Double precioPorHora; // Usando Double como pediste
    private String estado; // (Ej: "DISPONIBLE", "MANTENIMIENTO")
}
