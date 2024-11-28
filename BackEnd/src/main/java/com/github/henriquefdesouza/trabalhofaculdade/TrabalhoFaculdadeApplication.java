package com.github.henriquefdesouza.trabalhofaculdade;

import com.github.henriquefdesouza.trabalhofaculdade.model.Usuario;
import com.github.henriquefdesouza.trabalhofaculdade.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrabalhoFaculdadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoFaculdadeApplication.class, args);
    }

}