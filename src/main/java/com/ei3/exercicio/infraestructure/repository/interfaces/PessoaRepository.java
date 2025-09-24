package com.ei3.exercicio.infraestructure.repository.interfaces;

import com.ei3.exercicio.infraestructure.entity.Pessoa;
import java.util.List;
import java.util.Optional;

public interface PessoaRepository {

    void insert(Pessoa pessoa);
    List<Pessoa> getAll();
    Optional<Pessoa> getById(long id);
    List<Long> getAllIds();

}
