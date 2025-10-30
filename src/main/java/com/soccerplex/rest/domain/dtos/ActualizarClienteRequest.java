package com.soccerplex.rest.domain.dtos;
import lombok.Data;

@Data
public class ActualizarClienteRequest {
    private String nombre;
    private String telefono;
    private String correo;
    private String password; // Opcional, solo si se quiere cambiar
}
