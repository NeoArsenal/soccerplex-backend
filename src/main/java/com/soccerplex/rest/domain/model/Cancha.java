package com.soccerplex.rest.domain.model;

import lombok.Data;

@Data
public class Cancha {
    private Long id;
    private String nombre;
    private String tipo;
    private Double precioHora;
    private String estado;
}
