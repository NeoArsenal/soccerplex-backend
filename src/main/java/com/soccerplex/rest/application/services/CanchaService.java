package com.soccerplex.rest.application.services;

import com.soccerplex.rest.domain.dtos.CrearCanchaRequest;
import com.soccerplex.rest.domain.model.Cancha;
import com.soccerplex.rest.domain.repository.CanchaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CanchaService {

    private final CanchaRepository canchaRepository;

    public Cancha registrar(CrearCanchaRequest request) {
        Cancha cancha = new Cancha();
        cancha.setNombre(request.getNombre());
        cancha.setTipo(request.getTipo());
        cancha.setPrecioHora(request.getPrecioPorHora());
        cancha.setEstado(request.getEstado());
        // 'estado' no está en tu modelo de dominio 'Cancha',
        // así que lo manejamos en la entidad si es necesario, 
        // pero por ahora lo omitimos del modelo de dominio.
        
        return canchaRepository.save(cancha);
    }

    @Transactional(readOnly = true)
    public List<Cancha> findAll() {
        return canchaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cancha> findById(Long id) {
        return canchaRepository.findById(id);
    }
}
