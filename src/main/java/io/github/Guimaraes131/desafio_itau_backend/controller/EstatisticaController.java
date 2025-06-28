package io.github.Guimaraes131.desafio_itau_backend.controller;

import io.github.Guimaraes131.desafio_itau_backend.model.Estatistica;
import io.github.Guimaraes131.desafio_itau_backend.model.Transacao;
import io.github.Guimaraes131.desafio_itau_backend.repository.TransacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final TransacaoRepository repository;

    public EstatisticaController(TransacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Estatistica> get() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limite = agora.minusSeconds(60);

        List<Transacao> ultimasTransacoes = repository
                .findAll()
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
