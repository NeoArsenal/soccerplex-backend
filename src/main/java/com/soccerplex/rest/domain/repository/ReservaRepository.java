package com.soccerplex.rest.domain.repository;

import com.soccerplex.rest.domain.model.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    
    Reserva save(Reserva reserva);
    Optional<Reserva> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Reserva> findAll();
    List<Reserva> findByClienteId(Long clienteId);

    // ¡IMPORTANTE! Este es el método que tu lógica de negocio necesita
    // Es mucho más limpio que el que tenías
    List<Reserva> findOverlappingReservas(Long canchaId, LocalDate fecha, 
                                          LocalTime horaInicio, LocalTime horaFin);
}
