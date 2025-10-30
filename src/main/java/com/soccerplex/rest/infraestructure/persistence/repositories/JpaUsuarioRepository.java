package com.soccerplex.rest.infraestructure.persistence.repositories;

import com.soccerplex.rest.domain.model.Usuario;
import com.soccerplex.rest.domain.repository.UsuarioRepository;
import com.soccerplex.rest.infraestructure.persistence.entities.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaUsuarioRepository implements UsuarioRepository {

    private final SpringDataUsuarioRepository springDataRepo;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = UsuarioEntity.fromDomain(usuario);
        UsuarioEntity savedEntity = springDataRepo.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return springDataRepo.findById(id).map(UsuarioEntity::toDomain);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return springDataRepo.findByUsername(username).map(UsuarioEntity::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return springDataRepo.findAll().stream()
                .map(UsuarioEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataRepo.existsById(id);
    }
}
