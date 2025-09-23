package com.ei3.exercicio.domain.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.ei3.exercicio.domain.dto.CreateAlocacaoDto;
import com.ei3.exercicio.infraestructure.entity.Alocacao;

public interface AlocacaoService {
    boolean createAlocacao(CreateAlocacaoDto alocacaoDto);
    List<Alocacao> getAllAlocacao();
    double custoPeriodo(LocalDate dataInicio, LocalDate dataFim);
    double custoTotal();
}
