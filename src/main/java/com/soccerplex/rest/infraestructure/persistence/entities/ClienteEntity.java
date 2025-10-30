package com.soccerplex.rest.infraestructure.persistence.entities;

import com.soccerplex.rest.domain.model.Cliente;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente", uniqueConstraints = { 
    @UniqueConstraint(columnNames = "correo"),
    @UniqueConstraint(columnNames = "username"), // <-- Añadido
    @UniqueConstraint(columnNames = "documento")  // <-- Añadido
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity { // <-- 1. RENOMBRADO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "documento")
    private String documento;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "estado", length = 10) // Mantenemos String
    private String estado; // ACTIVO / INACTIVO

    // --- 2. TODOS LOS GETTERS Y SETTERS MANUALES FUERON ELIMINADOS ---
    // Lombok (@Data) se encarga de ellos.



    /**
     * MÉTODO ESTÁTICO (Factory)
     * Crea una Entidad (BD) a partir de un Modelo (Dominio).
     */
    public static ClienteEntity fromDomain(Cliente domain) {
        if (domain == null) return null;

        return ClienteEntity.builder()
                .id(domain.getId()) // Importante para actualizaciones
                .nombre(domain.getNombre())
                .telefono(domain.getTelefono())
                .correo(domain.getCorreo())
                .password(domain.getPassword())
                .estado(domain.getEstado())
                .documento(domain.getDocumento())
                .username(domain.getUsername())
                .build();
    }

    /**
     * MÉTODO DE INSTANCIA (Mapper)
     * Convierte esta Entidad (this) en un Modelo de Dominio.
     */
    public Cliente toDomain() {
        Cliente domain = new Cliente();
        domain.setId(this.id);
        domain.setNombre(this.nombre);
        domain.setTelefono(this.telefono);
        domain.setCorreo(this.correo);
        domain.setPassword(this.password);
        domain.setEstado(this.estado);
        domain.setDocumento(this.documento);
        domain.setUsername(this.username);
        return domain;
    }

}
