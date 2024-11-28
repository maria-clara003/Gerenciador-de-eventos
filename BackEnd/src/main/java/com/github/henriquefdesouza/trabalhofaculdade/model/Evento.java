package com.github.henriquefdesouza.trabalhofaculdade.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {
    @Id
    private String id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    @NotBlank(message = "Data é obrigatória")
    private String data;

    @NotBlank(message = "Local é obrigatório")
    private String local;

}