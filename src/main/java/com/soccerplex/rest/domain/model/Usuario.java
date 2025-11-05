package com.soccerplex.rest.domain.model;

import lombok.Data;
/**
 * Patrón aplicado: Entity (DDD)
 * Rol: Representa un objeto del dominio con identidad propia.
 * Justificación: Modela los objetos del negocio (Cancha, Cliente, Reserva)
 * y encapsula el estado y comportamiento del dominio.
 */
@Data
public class Usuario {

    private Long id;
    private String username;
    private String password; // El hash encriptado
    private String rol;      // ADMIN o STAFF
    private String estado;   // ACTIVO / INACTIVO
    
    public boolean isActivo() {
        return "ACTIVO".equalsIgnoreCase(this.estado);
    }
}
