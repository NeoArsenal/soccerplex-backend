package com.soccerplex.rest.infraestructure.web;

import com.soccerplex.rest.application.services.CanchaService;
import com.soccerplex.rest.domain.dtos.CanchaResponse;
import com.soccerplex.rest.domain.dtos.CrearCanchaRequest;
import com.soccerplex.rest.domain.model.Cancha;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/canchas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;

    @PostMapping
    public ResponseEntity<CanchaResponse> registrarCancha(@RequestBody CrearCanchaRequest request) {
        Cancha nueva = canchaService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CanchaResponse.fromDomain(nueva));
    }

    @GetMapping
    public ResponseEntity<List<CanchaResponse>> listarTodas() {
        List<Cancha> canchas = canchaService.findAll();
        List<CanchaResponse> response = canchas.stream()
                .map(CanchaResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanchaResponse> buscarPorId(@PathVariable Long id) {
        return canchaService.findById(id)
                .map(CanchaResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
