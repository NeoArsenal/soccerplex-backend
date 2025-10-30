package com.soccerplex.rest.domain.model;

import lombok.Data;

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