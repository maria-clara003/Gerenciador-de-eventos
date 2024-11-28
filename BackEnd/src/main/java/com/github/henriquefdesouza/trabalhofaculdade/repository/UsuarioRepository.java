package com.github.henriquefdesouza.trabalhofaculdade.repository;

import com.github.henriquefdesouza.trabalhofaculdade.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
