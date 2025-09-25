package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.List;

import com.ei3.exercicio.infraestructure.entity.Alocacao;

public interface AlocacaoRepository {
    Alocacao insert(Alocacao alocacao);
    List<Alocacao> all();
    List<Alocacao> findByProjetoId(Long projetoId);
    List<Alocacao> findByPerfilPessoaId(Long perfilPessoaId);
}
