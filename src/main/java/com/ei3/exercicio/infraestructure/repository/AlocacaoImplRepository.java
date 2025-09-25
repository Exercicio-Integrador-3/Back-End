package com.ei3.exercicio.infraestructure.repository;

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

    public Alocacao insert(Alocacao alocacao){
        return this.alocacaoRepo.save(alocacao);
    } 

    public List<Alocacao> all(){
        return this.alocacaoRepo.findAll();
    }

    public List<Alocacao> findByProjetoId(Long projetoId){
        return this.alocacaoRepo.findByProjetoId(projetoId);
    }

    public List<Alocacao> findByPerfilPessoaId(Long perfilPessoaId){
        return this.alocacaoRepo.findByPerfilPessoaId(perfilPessoaId);
    }
}
