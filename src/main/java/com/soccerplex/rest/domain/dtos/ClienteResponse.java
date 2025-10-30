package com.soccerplex.rest.domain.dtos; 

import com.soccerplex.rest.domain.model.Cliente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder // Usaremos builder para el mapeo
public class ClienteResponse {

    private Long id;
    private String nombre;
    private String telefono;
    private String correo;
    private String documento;
    private String username;
    private String estado;

    // --- Mapeo Manual (Estilo "fromDomain") ---
    
    public static ClienteResponse fromDomain(Cliente domain) {
        if (domain == null) return null;
        
        return ClienteResponse.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .telefono(domain.getTelefono())
                .correo(domain.getCorreo())
                .documento(domain.getDocumento())
                .username(domain.getUsername())
                .estado(domain.getEstado())
                .build();
    }
}