package com.soccerplex.rest.domain.model;

import lombok.Data;
/**
 * Patrón aplicado: Entity (DDD)
 * Rol: Representa un objeto del dominio con identidad propia.
 * Justificación: Modela los objetos del negocio (Cancha, Cliente, Reserva)
 * y encapsula el estado y comportamiento del dominio.
 */
@Data
public class Cancha {
    private Long id;
    private String nombre;
    private String tipo;
    private Double precioHora;
    private String estado;
}
