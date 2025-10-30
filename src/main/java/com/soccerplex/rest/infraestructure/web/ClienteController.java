package com.soccerplex.rest.infraestructure.web;

import com.soccerplex.rest.application.services.ClienteService;
// --- 1. IMPORTAMOS LOS DTOs ---
import com.soccerplex.rest.domain.dtos.ActualizarClienteRequest;
import com.soccerplex.rest.domain.dtos.ClienteResponse;
import com.soccerplex.rest.domain.dtos.LoginRequest;
import com.soccerplex.rest.domain.dtos.RegistrarClienteRequest;
// --- (Ya no importamos la Entidad Cliente) ---
import com.soccerplex.rest.domain.model.Cliente; // Importamos el MODELO de dominio

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor // <-- 2. Usamos Inyección por Constructor
public class ClienteController {

    private final ClienteService clienteService; // <-- 3. Inyección final
    
    // 🔹 Registrar nuevo cliente
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistrarClienteRequest request) { // <-- 4. USA DTO
        try {
            // 5. El servicio devuelve un MODELO de dominio
            Cliente clienteGuardado = clienteService.registrar(request);
            // 6. Convertimos el modelo a un DTO de respuesta (sin password)
            ClienteResponse response = ClienteResponse.fromDomain(clienteGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 🔹 Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) { // <-- 4. USA DTO
        // 5. El servicio ahora espera un DTO
        Optional<Cliente> encontrado = clienteService.login(request);

        if (encontrado.isPresent()) {
            // 6. Convertimos a DTO de respuesta
            ClienteResponse response = ClienteResponse.fromDomain(encontrado.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas o usuario inactivo");
        }
    }

    // 🔹 Actualizar cliente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ActualizarClienteRequest request) { // <-- 4. USA DTO
        try {
            // 5. El servicio espera el ID y el DTO
            Cliente clienteActualizado = clienteService.actualizar(id, request);
            // 6. Convertimos a DTO de respuesta
            ClienteResponse response = ClienteResponse.fromDomain(clienteActualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 🔹 Buscar cliente por correo
    @GetMapping("/buscar/{correo}")
    public ResponseEntity<ClienteResponse> buscarPorCorreo(@PathVariable String correo) { // <-- 7. Devuelve DTO
        return clienteService.buscarPorCorreo(correo) // El servicio devuelve Optional<Cliente>
                .map(ClienteResponse::fromDomain)     // Mapea Cliente -> ClienteResponse
                .map(ResponseEntity::ok)              // Si existe, envuelve en ResponseEntity.ok
                .orElse(ResponseEntity.notFound().build()); // Si no, devuelve 404
    }

    // 🔹 Eliminar cliente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            clienteService.eliminar(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } catch (RuntimeException e) {
            // (Sería bueno tener un manejo de excepciones más específico)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}