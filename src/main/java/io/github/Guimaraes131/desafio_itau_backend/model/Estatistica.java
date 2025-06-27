package io.github.Guimaraes131.desafio_itau_backend.model;

import java.math.BigDecimal;

public record Estatistica(
        Integer count,
        BigDecimal sum,
        BigDecimal avg,
        BigDecimal min,
        BigDecimal max) {

    public static Estatistica semRegistro() {
        return new Estatistica(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
