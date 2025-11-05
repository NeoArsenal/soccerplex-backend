package com.soccerplex.rest.domain.dtos;
import lombok.Data;
/**
 * Patrón aplicado: DTO (Data Transfer Object)
 * Rol: Transportar datos entre capas sin exponer las entidades internas.
 * Justificación: Reduce acoplamiento y protege el modelo de dominio.
 */
@Data
public class RegistrarClienteRequest {
    private String nombre;
    private String telefono;
    private String correo;
    private String password;
    private String documento;
    private String username;
}
