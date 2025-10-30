package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.infraestructure.persistence.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
    // Consulta mágica para el login
    Optional<UsuarioEntity> findByUsername(String username);
}
