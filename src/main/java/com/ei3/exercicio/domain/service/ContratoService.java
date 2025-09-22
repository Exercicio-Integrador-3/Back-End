package com.ei3.exercicio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ei3.exercicio.domain.dto.ContratoDto;
import com.ei3.exercicio.infraestructure.entity.Contrato;
import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.ContratoRepository;

public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;

    public boolean createContrato(ContratoDto contratoDto){
        var contratos = this.contratoRepository.getAllByPessoaId(contratoDto.getPessoaId());
        if(!contratos.isEmpty()){
            boolean resp = contratos.stream().filter(c -> c.getDataFim().isAfter(contratoDto.getDataInicio())).toList().isEmpty();
            if(!resp){
                return false;
            }
        }

        Contrato c = new Contrato(contratoDto.Id, new Pessoa(contratoDto.getPessoaId()),
                     new Perfil(contratoDto.getPerfilId()), contratoDto.getDataInicio(), contratoDto.getDataFim(),
                     contratoDto.getHorasSemanais(), contratoDto.getSalarioHora());

        this.contratoRepository.insert(c);
        return true;
    }

}
