package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.infraestructure.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, Long> {
    
    // Spring mágicamente crea esta consulta basándose en el nombre del método
    Optional<ClienteEntity> findByCorreo(String correo);

    // Spring ya nos da: save, findById, deleteById, existsById
}
