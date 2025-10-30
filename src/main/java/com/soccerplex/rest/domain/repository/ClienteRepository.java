package com.soccerplex.rest.domain.repository;

import com.soccerplex.rest.domain.model.Cliente;
import java.util.Optional;


public interface ClienteRepository {
    
    // Métodos que tu servicio necesita
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByCorreo(String correo);
    void deleteById(Long id);
    boolean existsById(Long id);
    
    // (Puedes añadir más métodos después si los necesitas)
}
