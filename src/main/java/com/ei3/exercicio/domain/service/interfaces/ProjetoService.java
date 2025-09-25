package com.ei3.exercicio.domain.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.ei3.exercicio.domain.dto.CreateProjetoDto;
import com.ei3.exercicio.domain.dto.ProjetoDto;

public interface ProjetoService {
    boolean createProject(CreateProjetoDto projetoDto);
    List<ProjetoDto> getAllProjetos();
    Optional<ProjetoDto> getProjetoById(long id);
    List<Long> getAllIds();

}