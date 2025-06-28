package io.github.Guimaraes131.desafio_itau_backend.controller;

import io.github.Guimaraes131.desafio_itau_backend.model.Estatistica;
import io.github.Guimaraes131.desafio_itau_backend.model.Transacao;
import io.github.Guimaraes131.desafio_itau_backend.repository.TransacaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoRepository repository;

    public TransacaoController(TransacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Transacao transacao) {
        repository.create(transacao);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> destroy() {
        repository.destroyAll();

        return ResponseEntity.ok().build();
    }
}
