package io.github.Guimaraes131.desafio_itau_backend.repository;

import io.github.Guimaraes131.desafio_itau_backend.model.Transacao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransacaoRepository {

    private final List<Transacao> repository;

    public TransacaoRepository(List<Transacao> repository) {
        this.repository = repository;
    }

    public void create(Transacao transacao) {
        repository.add(transacao);
    }

    public void destroyAll() {
        repository.clear();
    }

    public List<Transacao> findAll() {
        return List.copyOf(repository);
    }
}
