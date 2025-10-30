package com.soccerplex.rest.domain.dtos;

import com.soccerplex.rest.domain.model.Reserva;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Data
@Builder
public class ReservaResponse {
    private Long id;
    private String codigo;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double precio; // Usando Double
    private String estado;
    
    // --- DTOs Anidados ---
    private ClienteResponse cliente; 
    private CanchaResponse cancha;   

    public static ReservaResponse fromDomain(Reserva domain) {
        if (domain == null) return null;
        
        return ReservaResponse.builder()
                .id(domain.getId())
                .codigo(domain.getCodigo())
                .fecha(domain.getFecha())
                .horaInicio(domain.getHoraInicio())
                .horaFin(domain.getHoraFin())
                .precio(domain.getPrecio())
                .estado(domain.getEstado())
                // Mapea los modelos de dominio anidados a sus DTOs de respuesta
                .cliente(Optional.ofNullable(domain.getCliente())
                                 .map(ClienteResponse::fromDomain)
                                 .orElse(null))
                .cancha(Optional.ofNullable(domain.getCancha())
                                .map(CanchaResponse::fromDomain)
                                .orElse(null))
                .build();
    }
}
