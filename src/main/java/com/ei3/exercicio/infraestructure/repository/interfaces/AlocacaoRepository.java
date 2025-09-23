package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.ei3.exercicio.infraestructure.entity.Alocacao;

public interface AlocacaoRepository {
    void insert(Alocacao alocacao);
    List<Alocacao> all();
    List<Alocacao> custoPeriodo(LocalDate dataInicio, LocalDate dataFim);
    List<Alocacao> custoTotal();
}
