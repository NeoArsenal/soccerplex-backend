package com.soccerplex.rest.application.services;

// --- DOMAIN IMPORTS ---
// (¡SOLO importamos desde el dominio!)
import com.soccerplex.rest.domain.model.Reserva;
import com.soccerplex.rest.domain.model.Cliente;
import com.soccerplex.rest.domain.model.Cancha;
import com.soccerplex.rest.domain.repository.ReservaRepository;
import com.soccerplex.rest.domain.repository.ClienteRepository;
import com.soccerplex.rest.domain.repository.CanchaRepository;
import com.soccerplex.rest.domain.dtos.CrearReservaRequest;

// --- FRAMEWORK IMPORTS ---
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // <-- 1. Inyección por constructor
@Transactional
public class ReservaService {

    // 2. Dependemos de las INTERFACES del dominio
    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final CanchaRepository canchaRepository;

    
    public Reserva registrar(CrearReservaRequest request) {
        
        // 3. Buscamos los modelos de DOMINIO
        Cliente cliente = clienteRepository.findById(request.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            
        Cancha cancha = canchaRepository.findById(request.getCanchaId())
            .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        // 4. Lógica de negocio (Validación de solapamiento)
        validarDisponibilidad(request.getCanchaId(), request.getFecha(), 
                              request.getHoraInicio(), request.getHoraFin(), null);
        
        // 5. Mapeamos DTO a Modelo de DOMINIO
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setCancha(cancha);
        reserva.setFecha(request.getFecha());
        reserva.setHoraInicio(request.getHoraInicio());
        reserva.setHoraFin(request.getHoraFin());
        reserva.setPrecio(request.getPrecio()); 

        // Lógica de negocio
        reserva.setCodigo(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        reserva.setEstado("PENDIENTE");

        // 6. Guardamos el modelo de DOMINIO
        return reservaRepository.save(reserva);
    }

    // Método de validación privado
    private void validarDisponibilidad(Long canchaId, java.time.LocalDate fecha, 
                                       java.time.LocalTime horaInicio, 
                                       java.time.LocalTime horaFin, 
                                       Long reservaIdExcluir) {
        
        // Usamos el método limpio de nuestra interfaz de repositorio
        List<Reserva> ocupadas = reservaRepository.findOverlappingReservas(
                                    canchaId, fecha, horaInicio, horaFin);

        if (reservaIdExcluir != null) {
            ocupadas.removeIf(r -> r.getId().equals(reservaIdExcluir));
        }

        if (!ocupadas.isEmpty()) {
            throw new RuntimeException("La cancha ya está ocupada en ese horario");
        }
    }


    @Transactional(readOnly = true)
    public List<Reserva> listarPorCliente(Long clienteId) {
        // Devuelve modelos de DOMINIO
        return reservaRepository.findByClienteId(clienteId);
    }

    @Transactional(readOnly = true)
    public Optional<Reserva> buscarPorId(Long id) {
        // Devuelve un modelo de DOMINIO
        return reservaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarTodas() {
        // Esto corrige tu error de "Reserva" vs "ReservaEntity"
        return reservaRepository.findAll();
    }
    
    public void eliminar(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada con id: " + id);
        }
        reservaRepository.deleteById(id);
    }
}