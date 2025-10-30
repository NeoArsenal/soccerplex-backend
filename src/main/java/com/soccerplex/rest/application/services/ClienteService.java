// 1. CORREGIDO: Para que coincida con tu carpeta "services" (plural)
package com.soccerplex.rest.application.services;

// --- DOMAIN IMPORTS ---
// 2. CORREGIDO: Eliminamos "api.capas" de todas las importaciones
//    Asumimos que tu proyecto está estructurado bajo "com.soccerplex.rest"
import com.soccerplex.rest.domain.model.Cliente;
import com.soccerplex.rest.domain.repository.ClienteRepository;
import com.soccerplex.rest.domain.dtos.ActualizarClienteRequest;
import com.soccerplex.rest.domain.dtos.LoginRequest;
import com.soccerplex.rest.domain.dtos.RegistrarClienteRequest;

// --- FRAMEWORK IMPORTS ---
// (Estos probablemente estaban bien)
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor 
@Transactional         
public class ClienteService {

    // --- DEPENDENCIAS ---
    private final ClienteRepository clienteRepository; 
    private final PasswordEncoder passwordEncoder;

    // --- MÉTODOS ---

    public Cliente registrar(RegistrarClienteRequest request) {
        // Validar si el correo ya existe
        if (clienteRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        cliente.setCorreo(request.getCorreo());
        cliente.setPassword(passwordEncoder.encode(request.getPassword())); // Encriptar
        cliente.setEstado("ACTIVO");
        cliente.setDocumento(request.getDocumento());
        cliente.setUsername(request.getUsername());
        
        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true) 
    public Optional<Cliente> buscarPorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> login(LoginRequest request) {
        Optional<Cliente> clienteOpt = clienteRepository.findByCorreo(request.getCorreo());

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            if (cliente.getEstado().equalsIgnoreCase("ACTIVO") &&
                passwordEncoder.matches(request.getPassword(), cliente.getPassword())) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    public Cliente actualizar(Long id, ActualizarClienteRequest request) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        existente.setNombre(request.getNombre());
        existente.setTelefono(request.getTelefono());
        existente.setCorreo(request.getCorreo());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            existente.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return clienteRepository.save(existente);
    }

    public void eliminar(Long id) {
         if (!clienteRepository.existsById(id)) { 
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}