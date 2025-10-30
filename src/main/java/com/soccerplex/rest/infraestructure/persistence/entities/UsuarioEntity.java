package com.soccerplex.rest.infraestructure.persistence.entities;

import com.soccerplex.rest.domain.model.Usuario; // <-- 1. IMPORTA EL DOMINIO
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios", uniqueConstraints = { // <-- 2. Tabla en plural
    @UniqueConstraint(columnNames = "username")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity { // <-- 3. RENOMBRADO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "rol", length = 20)
    private String rol; // ADMIN o STAFF

    @Column(name = "estado", length = 10)
    private String estado; // ACTIVO / INACTIVO

    // --- 4. GETTERS/SETTERS MANUALES ELIMINADOS ---
    
    // --- 5. MÉTODOS DE MAPEO AÑADIDOS ---

    public static UsuarioEntity fromDomain(Usuario domain) {
        if (domain == null) return null;
        return UsuarioEntity.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .rol(domain.getRol())
                .estado(domain.getEstado())
                .build();
    }

    public Usuario toDomain() {
        Usuario domain = new Usuario();
        domain.setId(this.id);
        domain.setUsername(this.username);
        domain.setPassword(this.password);
        domain.setRol(this.rol);
        domain.setEstado(this.estado);
        return domain;
    }
}