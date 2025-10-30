package com.soccerplex.rest.infraestructure.web;

import com.soccerplex.rest.application.services.ReservaService;
// --- 1. IMPORTAMOS DTOs Y MODELOS ---
import com.soccerplex.rest.domain.dtos.CrearReservaRequest;
import com.soccerplex.rest.domain.dtos.ReservaResponse;
import com.soccerplex.rest.domain.model.Reserva;
// --- (¡No más imports de 'entities' o 'repositories'!) ---

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor // <-- 2. Inyección por Constructor
public class ReservaController {

    private final ReservaService reservaService; // <-- 3. ÚNICA dependencia

    // 🔹 Registrar nueva reserva
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarReserva(@RequestBody CrearReservaRequest request) { // <-- 4. USA DTO
        try {
            // 5. El servicio se encarga de TODA la lógica
            Reserva nuevaReserva = reservaService.registrar(request);
            
            // 6. Mapeamos a DTO de Respuesta
            ReservaResponse response = ReservaResponse.fromDomain(nuevaReserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        
        } catch (RuntimeException e) {
            // Captura errores como "Cliente no encontrado", "Cancha ocupada", etc.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 🔹 Listar reservas de un cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ReservaResponse>> listarPorCliente(@PathVariable Long clienteId) {
        List<Reserva> reservas = reservaService.listarPorCliente(clienteId);
        
        // Mapeamos la lista de Modelos a una lista de DTOs de Respuesta
        List<ReservaResponse> response = reservas.stream()
                .map(ReservaResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 🔹 Buscar reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> buscarPorId(@PathVariable Long id) {
        // Mapeo elegante de Optional
        return reservaService.buscarPorId(id)
                .map(ReservaResponse::fromDomain) // Convierte Reserva -> ReservaResponse
                .map(ResponseEntity::ok)           // Envuelve en ResponseEntity
                .orElse(ResponseEntity.notFound().build()); // Devuelve 404 si está vacío
    }

    // 🔹 Listar todas las reservas
    @GetMapping
    public ResponseEntity<List<ReservaResponse>> listarTodas() {
        List<Reserva> reservas = reservaService.listarTodas();
        List<ReservaResponse> response = reservas.stream()
                .map(ReservaResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    // 🔹 Cancelar (Eliminar) reserva
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            reservaService.eliminar(id);
            return ResponseEntity.ok("Reserva eliminada correctamente");
        } catch (RuntimeException e) { // Por si la reserva no existe
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    // (Nota: El método 'actualizar' requiere un DTO 'ActualizarReservaRequest' 
    //  y lógica en el servicio, similar a lo que hicimos con Cliente)
}
