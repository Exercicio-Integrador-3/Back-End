package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;

public interface PerfilPessoaRepository {
    List<PerfilPessoa> findByPessoaId(long pessoaId);
    Optional<PerfilPessoa> findById(long perfilPessoaId);
    PerfilPessoa insert(PerfilPessoa perfilPessoa);
}