package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.infraestructure.persistence.entities.CanchaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Patrón aplicado: Repository
 * Rol: Abstracción de la capa de acceso a datos.
 * Justificación: Permite interactuar con la base de datos sin exponer detalles
 * de persistencia al dominio o a los servicios.
 */
@Repository
public interface SpringDataCanchaRepository extends JpaRepository<CanchaEntity, Long> {
    // Spring ya nos da: save, findById, deleteById, existsById, findAll
}
