package com.soccerplex.rest.domain.dtos;

import lombok.Data;
/**
 * Patrón aplicado: DTO (Data Transfer Object)
 * Rol: Transportar datos entre capas sin exponer las entidades internas.
 * Justificación: Reduce acoplamiento y protege el modelo de dominio.
 */

@Data
public class LoginUsuarioRequest {
    private String username;
    private String password;
}
