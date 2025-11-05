package com.soccerplex.rest.domain.dtos;
import lombok.Data;
/**
 * Patrón aplicado: DTO (Data Transfer Object)
 * Rol: Transportar datos entre capas sin exponer las entidades internas.
 * Justificación: Reduce acoplamiento y protege el modelo de dominio.
 */

@Data
public class ActualizarClienteRequest {
    private String nombre;
    private String telefono;
    private String correo;
    private String password; // Opcional, solo si se quiere cambiar
}
