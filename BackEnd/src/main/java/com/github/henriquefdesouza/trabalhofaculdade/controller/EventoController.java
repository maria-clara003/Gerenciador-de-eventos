package com.github.henriquefdesouza.trabalhofaculdade.controller;

import com.github.henriquefdesouza.trabalhofaculdade.model.Evento;
import com.github.henriquefdesouza.trabalhofaculdade.service.EventoService;
import com.github.henriquefdesouza.trabalhofaculdade.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private JwtUtil jwtUtil;

    // Listar todos os eventos
    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    // Obter evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable String id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar um novo evento
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento, @RequestHeader("Authorization") String token) {
        // Valida se o token JWT é válido
        if (jwtUtil.isValidToken(token)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String emailUsuario = authentication.getName();

            Evento novoEvento = eventoService.createEvento(evento);
            return ResponseEntity.status(201).body(novoEvento);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    // Atualizar evento existente
    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable String id, @RequestBody Evento evento) {
        Evento eventoAtualizado = eventoService.updateEvento(id, evento);
        return ResponseEntity.ok(eventoAtualizado);
    }

    // Deletar evento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable String id) {
        boolean deletado = eventoService.deleteEvento(id);
        if (deletado) {
            return ResponseEntity.noContent().build();  // Retorna 204 (sem conteúdo) se o evento foi deletado
        }
        return ResponseEntity.notFound().build();  // Retorna 404 (não encontrado) caso o evento não exista
    }
}
