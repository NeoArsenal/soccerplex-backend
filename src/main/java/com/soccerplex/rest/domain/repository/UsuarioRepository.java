package com.soccerplex.rest.domain.repository;

import com.soccerplex.rest.domain.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
