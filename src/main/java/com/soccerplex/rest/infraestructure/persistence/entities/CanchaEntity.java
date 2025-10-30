package com.soccerplex.rest.infraestructure.persistence.entities;

import com.soccerplex.rest.domain.model.Cancha;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "cancha") // Le dice a JPA que la tabla se llama "canchas"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanchaEntity { // <-- RENOMBRADO a "CanchaEntity"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "precio_hora") // <-- Nombre en la BD (snake_case)
    private Double precioHora; // <-- CORREGIDO: Nombre en Java (camelCase)

    @Column(name = "estado")
    private String estado;

// --- MÉTODOS DE MAPEO ---

    public static CanchaEntity fromDomain(Cancha domain) {
        if (domain == null) return null;
        return CanchaEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .tipo(domain.getTipo())
                .precioHora(domain.getPrecioHora())
                .estado(domain.getEstado())
                // Nota: 'estado' no está en tu modelo de dominio 'Cancha',
                // así que no lo mapeamos desde ahí.
                // Se manejaría con lógica interna si fuera necesario.
                .build();
    }

    public Cancha toDomain() {
        Cancha domain = new Cancha();
        domain.setId(this.id);
        domain.setNombre(this.nombre);
        domain.setTipo(this.tipo);
        domain.setPrecioHora(this.precioHora);
        domain.setEstado(this.estado);
        // Nota: No mapeamos 'estado' al dominio,
        // ya que tu modelo 'Cancha' no lo tiene.
        return domain;
    }

}





