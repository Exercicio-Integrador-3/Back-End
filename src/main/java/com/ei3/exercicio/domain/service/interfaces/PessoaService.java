package com.ei3.exercicio.domain.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.ei3.exercicio.domain.dto.CreatePessoaDto;
import com.ei3.exercicio.domain.dto.PessoaDto;

public interface PessoaService {
    boolean createPessoa(CreatePessoaDto pessoaDto);
    List<PessoaDto> getAllPessoas();
    Optional<PessoaDto> getPessoaById(long id);
    List<Long> getAllIds();


}
