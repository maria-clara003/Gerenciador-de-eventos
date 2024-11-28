package com.github.henriquefdesouza.trabalhofaculdade.service;

import com.github.henriquefdesouza.trabalhofaculdade.model.Evento;
import com.github.henriquefdesouza.trabalhofaculdade.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    // Listar todos os eventos
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    // Obter um evento pelo ID
    public Optional<Evento> getEventoById(String id) {
        return eventoRepository.findById(id);
    }

    // Criar um novo evento
    public Evento createEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Atualizar um evento existente
    public Evento updateEvento(String id, Evento evento) {
        if (eventoRepository.existsById(id)) {
            evento.setId(id);
            return eventoRepository.save(evento);
        } else {
            throw new RuntimeException("Evento com ID " + id + " não encontrado");
        }
    }

    // Deletar um evento pelo ID
    public boolean deleteEvento(String id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
