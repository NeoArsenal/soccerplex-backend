package com.soccerplex.rest.domain.dtos;

import lombok.Data;

@Data
public class LoginUsuarioRequest {
    private String username;
    private String password;
}
