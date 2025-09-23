package com.ei3.exercicio.domain.service.interfaces;

import java.util.List;

import com.ei3.exercicio.domain.dto.ContratoDto;
import com.ei3.exercicio.domain.dto.CreateContratoDto;

public interface ContratoService {
    public boolean createContrato(CreateContratoDto contratoDto);
    public List<ContratoDto> getAllContratos();
    public List<ContratoDto> getById(long id);
}
