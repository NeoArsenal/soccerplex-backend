package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.domain.model.Reserva;
import com.soccerplex.rest.domain.repository.ReservaRepository;
import com.soccerplex.rest.infraestructure.persistence.entities.ReservaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Patrón aplicado: Repository
 * Rol: Abstracción de la capa de acceso a datos.
 * Justificación: Permite interactuar con la base de datos sin exponer detalles
 * de persistencia al dominio o a los servicios.
 */
@Repository
@RequiredArgsConstructor
public class JpaReservaRepository implements ReservaRepository {

    private final SpringDataReservaRepository springDataRepo;

    @Override
    public Reserva save(Reserva reserva) {
        ReservaEntity entity = ReservaEntity.fromDomain(reserva);
        ReservaEntity savedEntity = springDataRepo.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        return springDataRepo.findById(id).map(ReservaEntity::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataRepo.existsById(id);
    }

    @Override
    public List<Reserva> findAll() {
        return springDataRepo.findAll().stream()
                .map(ReservaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reserva> findByClienteId(Long clienteId) {
        return springDataRepo.findByCliente_Id(clienteId).stream()
                .map(ReservaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reserva> findOverlappingReservas(Long canchaId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        return springDataRepo.findOverlappingReservas(canchaId, fecha, horaInicio, horaFin).stream()
                .map(ReservaEntity::toDomain)
                .collect(Collectors.toList());
    }
}
