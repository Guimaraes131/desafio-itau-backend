package io.github.Guimaraes131.desafio_itau_backend.controller;

import io.github.Guimaraes131.desafio_itau_backend.model.Transacao;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final ArrayList<Transacao> repositorio;

    public TransacaoController(ArrayList<Transacao> repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Transacao transacao) {
        repositorio.add(transacao);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> destroy() {
        repositorio.removeAll(repositorio);

        return ResponseEntity.ok().build();
    }
}
