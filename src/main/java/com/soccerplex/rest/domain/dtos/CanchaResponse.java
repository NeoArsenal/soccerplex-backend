package com.soccerplex.rest.domain.dtos;

import com.soccerplex.rest.domain.model.Cancha;
import lombok.Builder;
import lombok.Data;
/**
 * Patrón aplicado: DTO (Data Transfer Object)
 * Rol: Transportar datos entre capas sin exponer las entidades internas.
 * Justificación: Reduce acoplamiento y protege el modelo de dominio.
 */
@Data
@Builder
public class CanchaResponse {
    private Long id;
    private String nombre;
    private String tipo;
    private Double precioPorHora; // Usando Double como pediste
    private String estado; // (Ej: "DISPONIBLE", "MANTENIMIENTO")

    public static CanchaResponse fromDomain(Cancha domain) {
        if (domain == null) return null;
        return CanchaResponse.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .tipo(domain.getTipo())
                .precioPorHora(domain.getPrecioHora())
                .estado(domain.getEstado())
                .build();
    }
}
