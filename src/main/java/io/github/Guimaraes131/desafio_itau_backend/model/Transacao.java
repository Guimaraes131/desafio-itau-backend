package io.github.Guimaraes131.desafio_itau_backend.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class Transacao {

    @NotNull(message = "campo valor é obrigatório")
    @PositiveOrZero(message = "campo valor deve ser maior ou igual a 0 (zero)")
    private BigDecimal valor;

    @NotNull(message = "campo dataHora é obrigatório")
    @Past(message = "campo dataHora deve ser no passado")
    private OffsetDateTime dataHora;
}
