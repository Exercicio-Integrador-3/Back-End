package com.ei3.exercicio.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei3.exercicio.domain.dto.AlocacaoDto;
import com.ei3.exercicio.domain.dto.CreateAlocacaoDto;
import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.AlocacaoId;
import com.ei3.exercicio.infraestructure.repository.interfaces.AlocacaoRepository;

@Service
public class AlocacaoImplService {
    
    @Autowired
    private AlocacaoRepository alocacaoRepository;

    public AlocacaoImplService(){}

    public boolean createAlocacao(CreateAlocacaoDto alocacaoDto){
        AlocacaoId aId = new AlocacaoId(alocacaoDto.idPessoa(), alocacaoDto.idProjeto());
        Alocacao a = new Alocacao(aId, alocacaoDto.quantidadeHoras());

        this.alocacaoRepository.insert(a);

        return true;
    }

    public List<AlocacaoDto> getAllAlocacao(){
        return this.alocacaoRepository.all()
        .stream()
        .map(a -> new AlocacaoDto(a.getPessoa().getId(), a.getProjeto().getId(), a.getQuantidadeHoras()))
        .toList();
    }

    public double custoTotal(){
        double valor;
        this.alocacaoRepository.custoTotal().stream().map(a -> a.getPessoa().getId()).forEach(id -> ); // getbyid da pessoa para encontrar o contrato e fazer o cálculo
    }

    public double custoPeriodo(LocalDate dataInicio, LocalDate dataFim){
        
    }
}
