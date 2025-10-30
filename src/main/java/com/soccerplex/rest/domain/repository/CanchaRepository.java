package com.soccerplex.rest.domain.repository;

// Importa el modelo de dominio, NO la entidad
import com.soccerplex.rest.domain.model.Cancha;
import java.util.List;
import java.util.Optional;

/**
 * Esto es un Puerto de Salida (Output Port).
 * Define el contrato que la capa de Aplicación (servicios) espera
 * para interactuar con la persistencia de Canchas, sin saber
 * qué base de datos se está usando.
 */
public interface CanchaRepository {

    // Método que necesita tu ReservaService
    Optional<Cancha> findById(Long id);

    // Métodos comunes que seguramente necesitarás
    Cancha save(Cancha cancha);
    
    List<Cancha> findAll();
    
    void deleteById(Long id);
    
    boolean existsById(Long id);

}
