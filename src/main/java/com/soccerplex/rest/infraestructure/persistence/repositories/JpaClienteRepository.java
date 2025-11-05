package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.domain.model.Cliente;
import com.soccerplex.rest.domain.repository.ClienteRepository;
import com.soccerplex.rest.infraestructure.persistence.entities.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Patrón aplicado: Repository
 * Rol: Abstracción de la capa de acceso a datos.
 * Justificación: Permite interactuar con la base de datos sin exponer detalles
 * de persistencia al dominio o a los servicios.
 */
@Repository
@RequiredArgsConstructor
public class JpaClienteRepository implements ClienteRepository {

    private final SpringDataClienteRepository springDataRepo;

    @Override
    public Cliente save(Cliente cliente) {
        // 1. Traduce a Entidad (usando el método estático de la entidad)
        ClienteEntity entity = ClienteEntity.fromDomain(cliente);
        
        // 2. Guarda
        ClienteEntity entityGuardada = springDataRepo.save(entity);
        
        // 3. Traduce de vuelta a Dominio (usando el método de instancia)
        return entityGuardada.toDomain();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return springDataRepo.findById(id)
                             .map(ClienteEntity::toDomain); // <-- ¡Qué limpio!
    }

    @Override
    public Optional<Cliente> findByCorreo(String correo) {
        return springDataRepo.findByCorreo(correo)
                             .map(ClienteEntity::toDomain); // <-- ¡Súper legible!
    }

    @Override
    public void deleteById(Long id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataRepo.existsById(id);
    }

    // --- ¡TODOS LOS MÉTODOS DE MAPEO PRIVADOS FUERON ELIMINADOS! ---
}
