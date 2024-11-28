package com.github.henriquefdesouza.trabalhofaculdade.repository;

import com.github.henriquefdesouza.trabalhofaculdade.model.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventoRepository extends MongoRepository<Evento, String> {
}
