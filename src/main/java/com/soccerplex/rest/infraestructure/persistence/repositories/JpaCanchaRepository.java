package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.domain.model.Cancha;
import com.soccerplex.rest.domain.repository.CanchaRepository;
import com.soccerplex.rest.infraestructure.persistence.entities.CanchaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List; // <-- 1. Importa List
import java.util.Optional;
import java.util.stream.Collectors; // <-- 2. Importa Collectors
/**
 * Patrón aplicado: Repository
 * Rol: Abstracción de la capa de acceso a datos.
 * Justificación: Permite interactuar con la base de datos sin exponer detalles
 * de persistencia al dominio o a los servicios.
 */
@Repository
@RequiredArgsConstructor
public class JpaCanchaRepository implements CanchaRepository {

    private final SpringDataCanchaRepository springDataRepo;

    @Override
    public Optional<Cancha> findById(Long id) {
        return springDataRepo.findById(id)
                             .map(CanchaEntity::toDomain);
    }
    
    // --- MÉTODOS AÑADIDOS QUE FALTABAN ---

    @Override
    public Cancha save(Cancha cancha) {
        // 1. Convierte Dominio -> Entidad
        CanchaEntity entity = CanchaEntity.fromDomain(cancha);
        // 2. Guarda la Entidad
        CanchaEntity savedEntity = springDataRepo.save(entity);
        // 3. Devuelve el Dominio
        return savedEntity.toDomain();
    }

    @Override
    public List<Cancha> findAll() {
        // 1. Busca todas las Entidades
        return springDataRepo.findAll().stream()
                // 2. Mapea cada una a Dominio
                .map(CanchaEntity::toDomain)
                // 3. Devuelve la lista
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        // Simplemente llama al repositorio de Spring
        springDataRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        // Simplemente llama al repositorio de Spring
        return springDataRepo.existsById(id);
    }
}
