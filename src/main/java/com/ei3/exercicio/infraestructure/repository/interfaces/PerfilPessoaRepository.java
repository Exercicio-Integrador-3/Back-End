package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.Optional;

import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;

public interface PerfilPessoaRepository {
    Optional<PerfilPessoa> findByPessoaId(long pessoaId);
    Optional<PerfilPessoa> findById(long perfilPessoaId);
    PerfilPessoa insert(PerfilPessoa perfilPessoa);
}