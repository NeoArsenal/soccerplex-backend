package com.soccerplex.rest.domain.dtos;
import lombok.Data;

@Data
public class RegistrarClienteRequest {
    private String nombre;
    private String telefono;
    private String correo;
    private String password;
    private String documento;
    private String username;
}
