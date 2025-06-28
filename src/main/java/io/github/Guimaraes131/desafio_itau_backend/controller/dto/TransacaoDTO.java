package io.github.Guimaraes131.desafio_itau_backend.controller.dto;

import io.github.Guimaraes131.desafio_itau_backend.model.Transacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoDTO(

        @NotNull(message = "campo valor é obrigatório")
        @PositiveOrZero(message = "campo valor deve ser maior ou igual a 0 (zero)")
        BigDecimal valor,

        @NotNull(message = "campo dataHora é obrigatório")
        @Past(message = "campo dataHora deve ser no passado")
        OffsetDateTime dataHora
) {

    public Transacao mapearParaTransacao() {
        Transacao transacao = new Transacao(this.valor, this.dataHora());

        return transacao;
    }
}
