package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;

public interface PerfilPessoaRepository {
    List<PerfilPessoa> findByPessoaId(long pessoaId);
    Optional<PerfilPessoa> findByPessoaIdAndTipoPerfil(long pessoaId, TipoPerfil tipoPerfil);
    Optional<PerfilPessoa> findById(long perfilPessoaId);
    PerfilPessoa insert(PerfilPessoa perfilPessoa);
}