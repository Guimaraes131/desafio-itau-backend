package io.github.Guimaraes131.desafio_itau_backend.controller;

import io.github.Guimaraes131.desafio_itau_backend.model.Estatistica;
import io.github.Guimaraes131.desafio_itau_backend.model.Transacao;
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

    @GetMapping("/estatistica")
    public ResponseEntity<Estatistica> get() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limite = agora.minusSeconds(60);

        List<Transacao> ultimasTransacoes = repositorio
                .stream()
                .filter(t -> t.getDataHora().isAfter(limite))
                .toList();

        Integer count = ultimasTransacoes.size();

        BigDecimal soma = ultimasTransacoes
                .stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Optional<BigDecimal> menorValor = ultimasTransacoes.stream()
                .map(Transacao::getValor)
                .min(Comparator.naturalOrder());

        Optional<BigDecimal> maiorValor = ultimasTransacoes.stream()
                .map(Transacao::getValor)
                .max(Comparator.naturalOrder());

        if (maiorValor.isPresent()) {
            Estatistica estatistica = new Estatistica(
                    count,
                    soma,
                    soma.divide(BigDecimal.valueOf(count), 3, RoundingMode.HALF_UP),
                    menorValor.get(),
                    maiorValor.get()
            );

            return ResponseEntity.ok(estatistica);
        }

        return ResponseEntity.ok(Estatistica.semRegistro());
    }
}
