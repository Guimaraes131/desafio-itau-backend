package io.github.Guimaraes131.desafio_itau_backend.controller;

import io.github.Guimaraes131.desafio_itau_backend.controller.dto.TransacaoDTO;
import io.github.Guimaraes131.desafio_itau_backend.repository.TransacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@Slf4j
public class TransacaoController {

    private final TransacaoRepository repository;

    public TransacaoController(TransacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid TransacaoDTO dto) {
        repository.create(dto.mapearParaTransacao());
        log.info("Salvando a transacao no repositorio");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> destroy() {
        repository.destroyAll();
        log.info("Excluindo todas as transacoes do repositorio");

        return ResponseEntity.ok().build();
    }
}
