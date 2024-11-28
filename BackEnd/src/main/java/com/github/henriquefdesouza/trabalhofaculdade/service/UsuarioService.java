package com.github.henriquefdesouza.trabalhofaculdade.service;

import com.github.henriquefdesouza.trabalhofaculdade.exception.UsuarioDuplicadoException;
import com.github.henriquefdesouza.trabalhofaculdade.model.Usuario;
import com.github.henriquefdesouza.trabalhofaculdade.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registro de novo usuário
    public Usuario registerUsuario(Usuario usuario) {
        // Verifica se o email já existe no banco
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new UsuarioDuplicadoException("Já existe um usuário com esse email.");
        }

        // Se não existir, codifica a senha e salva o novo usuário
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> authenticateUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent() && passwordEncoder.matches(usuario.getSenha(), usuarioExistente.get().getSenha())) {
            return usuarioExistente;
        }
        return Optional.empty();
    }

}
