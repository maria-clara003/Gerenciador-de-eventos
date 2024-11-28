package com.github.henriquefdesouza.trabalhofaculdade.controller;

import com.github.henriquefdesouza.trabalhofaculdade.model.Usuario;
import com.github.henriquefdesouza.trabalhofaculdade.service.UsuarioService;
import com.github.henriquefdesouza.trabalhofaculdade.util.JwtUtil;
import com.github.henriquefdesouza.trabalhofaculdade.exception.UsuarioDuplicadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    // Registro de novo usuário
    @PostMapping("/registrar")
    public ResponseEntity<?> registerUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.registerUsuario(usuario);
            return ResponseEntity.ok(novoUsuario);  // Sucesso ao registrar
        } catch (UsuarioDuplicadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());  // Email já registrado
        }
    }

    // Login de usuário
    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioAutenticado = usuarioService.authenticateUsuario(usuario);
        if (usuarioAutenticado.isPresent()) {
            String token = jwtUtil.generateToken(usuarioAutenticado.get().getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}
