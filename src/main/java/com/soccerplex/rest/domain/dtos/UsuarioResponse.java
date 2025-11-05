package com.soccerplex.rest.domain.dtos;

import com.soccerplex.rest.domain.model.Usuario;
import lombok.Builder;
import lombok.Data;
/**
 * Patrón aplicado: DTO (Data Transfer Object)
 * Rol: Transportar datos entre capas sin exponer las entidades internas.
 * Justificación: Reduce acoplamiento y protege el modelo de dominio.
 */
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
