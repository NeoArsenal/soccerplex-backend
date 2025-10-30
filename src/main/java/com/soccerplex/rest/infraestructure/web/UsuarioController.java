package com.soccerplex.rest.infraestructure.web;

import com.soccerplex.rest.application.services.UsuarioService;
// --- 1. IMPORTAMOS LOS DTOs Y EL MODELO ---
import com.soccerplex.rest.domain.dtos.LoginUsuarioRequest;
import com.soccerplex.rest.domain.dtos.RegistrarUsuarioRequest;
import com.soccerplex.rest.domain.dtos.UsuarioResponse;
import com.soccerplex.rest.domain.model.Usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // <-- Importa Optional
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios") // <-- Endpoint base para Staff/Admins
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService; // <-- Única dependencia

    /**
     * Endpoint para registrar un nuevo usuario (Admin/Staff).
     */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistrarUsuarioRequest request) {
        try {
            // 1. El servicio hace la lógica (validar, encriptar, guardar)
            Usuario nuevoUsuario = usuarioService.registrar(request);
            // 2. Mapeamos a un DTO de respuesta (sin contraseña)
            UsuarioResponse response = UsuarioResponse.fromDomain(nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            // Captura el error "El nombre de usuario ya existe"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint para Login de Admin/Staff.
     * Este es el método que corregimos para evitar el conflicto de tipos.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuarioRequest request) {
        
        Optional<Usuario> usuarioOpt = usuarioService.login(request);

        // Hacemos la validación con un if, que es más claro
        if (usuarioOpt.isEmpty()) {
            // Caso de Fallo
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Credenciales incorrectas o usuario inactivo");
        }

        // Caso de Éxito
        UsuarioResponse response = UsuarioResponse.fromDomain(usuarioOpt.get());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para obtener todos los usuarios (Admin/Staff).
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.findAll();
        // Mapeamos la lista de Modelos a una lista de DTOs de Respuesta
        List<UsuarioResponse> response = usuarios.stream()
                .map(UsuarioResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para obtener un usuario por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
        return usuarioService.findById(id) // Devuelve Optional<Usuario>
                .map(UsuarioResponse::fromDomain) // Convierte Usuario -> UsuarioResponse
                .map(ResponseEntity::ok)          // Envuelve en 200 OK
                .orElse(ResponseEntity.notFound().build()); // O devuelve 404
    }

    /**
     * Endpoint para eliminar un usuario por su ID.
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            // Captura el error "Usuario no encontrado"
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}