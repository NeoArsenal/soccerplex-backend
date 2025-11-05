package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.infraestructure.persistence.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Patrón aplicado: Repository
 * Rol: Abstracción de la capa de acceso a datos.
 * Justificación: Permite interactuar con la base de datos sin exponer detalles
 * de persistencia al dominio o a los servicios.
 */
@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
    // Consulta mágica para el login
    Optional<UsuarioEntity> findByUsername(String username);
}
