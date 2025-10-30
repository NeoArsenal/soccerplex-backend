package com.soccerplex.rest.domain.dtos;

import com.soccerplex.rest.domain.model.Usuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {
    
    private Long id;
    private String username;
    private String rol;
    private String estado;

    // Método de mapeo (como el que hicimos para ClienteResponse)
    public static UsuarioResponse fromDomain(Usuario domain) {
        if (domain == null) return null;
        return UsuarioResponse.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .rol(domain.getRol())
                .estado(domain.getEstado())
                .build();
    }
}
