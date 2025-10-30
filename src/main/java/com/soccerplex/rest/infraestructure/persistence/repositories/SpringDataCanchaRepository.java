package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.infraestructure.persistence.entities.CanchaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCanchaRepository extends JpaRepository<CanchaEntity, Long> {
    // Spring ya nos da: save, findById, deleteById, existsById, findAll
}
