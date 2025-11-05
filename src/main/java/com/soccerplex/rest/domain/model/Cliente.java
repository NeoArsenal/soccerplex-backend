package com.soccerplex.rest.domain.model;

import lombok.Data;
/**
 * Patrón aplicado: Entity (DDD)
 * Rol: Representa un objeto del dominio con identidad propia.
 * Justificación: Modela los objetos del negocio (Cancha, Cliente, Reserva)
 * y encapsula el estado y comportamiento del dominio.
 */
@Data
public class Cliente {
    
    private Long id;
    private String nombre;
    private String telefono;
    private String correo;
    private String password; // <-- Este es el hash encriptado
    private String estado;
    
    // --- CAMPOS AÑADIDOS ---
    // (Para que coincida con tu entidad de persistencia)
    private String documento;  
    private String username;   
    
    // (Tu regla de negocio)
    public boolean isActivo() {
        return "ACTIVO".equalsIgnoreCase(this.estado);
    }
}
