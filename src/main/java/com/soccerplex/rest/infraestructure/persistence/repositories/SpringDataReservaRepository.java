package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.infraestructure.persistence.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
/**
 * Patrón aplicado: Repository
 * Rol: Abstracción de la capa de acceso a datos.
 * Justificación: Permite interactuar con la base de datos sin exponer detalles
 * de persistencia al dominio o a los servicios.
 */

@Repository
public interface SpringDataReservaRepository extends JpaRepository<ReservaEntity, Long> {
    
    // Para el método "listarPorCliente"
    List<ReservaEntity> findByCliente_Id(Long clienteId);

    // Para la validación de solapamiento (más eficiente)
    @Query("SELECT r FROM ReservaEntity r " +
           "WHERE r.cancha.id = :canchaId AND r.fecha = :fecha " +
           "AND r.horaInicio < :horaFin AND r.horaFin > :horaInicio")
    List<ReservaEntity> findOverlappingReservas(
            @Param("canchaId") Long canchaId,
            @Param("fecha") LocalDate fecha,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin
    );
}
