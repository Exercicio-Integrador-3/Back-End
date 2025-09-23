package com.ei3.exercicio.infraestructure.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.repository.interfaces.AlocacaoRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.AlocacaoRepositoryJPA;

@Repository
public class AlocacaoImplRepository implements AlocacaoRepository{
    
    @Autowired
    private AlocacaoRepositoryJPA alocacaoRepo;

    public AlocacaoImplRepository(){}

    public void insert(Alocacao alocacao){
        this.alocacaoRepo.save(alocacao);
    } 

    public List<Alocacao> all(){
        return this.alocacaoRepo.findAll();
    }

    public List<Alocacao> custoPeriodo(LocalDate dataInicio, LocalDate dataFim){
        return this.alocacaoRepo.findAll();
    }

    public List<Alocacao> custoTotal(){
        return this.alocacaoRepo.findAll();
    }
}
