package com.soccerplex.rest.domain.dtos;

import lombok.Data;

@Data
public class RegistrarUsuarioRequest {
    private String username;
    private String password;
    private String rol; // (Ej: "ADMIN", "STAFF")
}