package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.List;

import com.ei3.exercicio.infraestructure.entity.Alocacao;

public interface AlocacaoRepository {
    void insert(Alocacao alocacao);
    List<Alocacao> all();
}
