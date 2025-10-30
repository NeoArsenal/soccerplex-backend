package com.soccerplex.rest.infraestructure.persistence.entities;

import com.soccerplex.rest.domain.model.Reserva; // <-- IMPORTA DOMINIO
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Optional;


@Entity
@Table(name = "reserva") // <-- "reserva"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaEntity { // <-- 3. CAMBIO: Renombrada a "ReservaEntity"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente; // <-- ¡YA SE USA! // <-- 4. CAMBIO: Relación con ClienteEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancha_id")
    private CanchaEntity cancha; // <-- 5. CAMBIO CRÍTICO: Ahora es una relación

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "estado", length = 20)
    private String estado; // PENDIENTE, CONFIRMADA, CANCELADA

    @Column(name = "precio")
    private double precio; // <-- 6. CAMBIO: Usamos BigDecimal

    @Column(name = "codigo", unique = true, length = 10)
    private String codigo;

    // --- 7. TODOS LOS GETTERS Y SETTERS MANUALES FUERON ELIMINADOS ---
    // Lombok (@Data) se encarga de ellos.

    // --- MÉTODOS DE MAPEO ---

    public static ReservaEntity fromDomain(Reserva domain) {
        if (domain == null) return null;
        
        // Convertimos los modelos de dominio anidados en entidades
        ClienteEntity clienteEntity = Optional.ofNullable(domain.getCliente())
                                              .map(ClienteEntity::fromDomain)
                                              .orElse(null);
        CanchaEntity canchaEntity = Optional.ofNullable(domain.getCancha())
                                            .map(CanchaEntity::fromDomain)
                                            .orElse(null);

        return ReservaEntity.builder()
                .id(domain.getId())
                .codigo(domain.getCodigo())
                .fecha(domain.getFecha())
                .horaInicio(domain.getHoraInicio())
                .horaFin(domain.getHoraFin())
                .precio(domain.getPrecio())
                .estado(domain.getEstado())
                .cliente(clienteEntity)
                .cancha(canchaEntity)
                .build();
    }

    public Reserva toDomain() {
        Reserva domain = new Reserva();
        domain.setId(this.id);
        domain.setCodigo(this.codigo);
        domain.setFecha(this.fecha);
        domain.setHoraInicio(this.horaInicio);
        domain.setHoraFin(this.horaFin);
        domain.setPrecio(this.precio);
        domain.setEstado(this.estado);
        
        // Convertimos las entidades anidadas en modelos de dominio
        // (Manejo de Nulos por si la carga es LAZY y no se cargó)
        if (this.cliente != null) {
            domain.setCliente(this.cliente.toDomain());
        }
        if (this.cancha != null) {
            domain.setCancha(this.cancha.toDomain());
        }
        return domain;
    }

}
