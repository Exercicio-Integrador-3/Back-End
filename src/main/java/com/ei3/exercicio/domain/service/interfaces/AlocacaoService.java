package com.ei3.exercicio.domain.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.ei3.exercicio.domain.dto.AlocacaoDto;
import com.ei3.exercicio.domain.dto.CreateAlocacaoDto;

public interface AlocacaoService {
    boolean createAlocacao(CreateAlocacaoDto alocacaoDto);
    List<AlocacaoDto> getAllAlocacao();
    double custoPeriodo(long idProjeto, LocalDate dataInicio, LocalDate dataFim);
    double custoTotal(long idProjeto);
}
