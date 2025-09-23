package com.ei3.exercicio.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ei3.exercicio.infraestructure.entity.Contrato;
import com.ei3.exercicio.infraestructure.repository.interfaces.ContratoRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.ContratoRepositoryJPA;

@Repository
public class ContratoImplRepository implements ContratoRepository {

    @Autowired
    private ContratoRepositoryJPA contratoRepo; 

    public ContratoImplRepository(){}

    @Override
    public Contrato insert(Contrato contrato){
        return this.contratoRepo.save(contrato);
    }

    @Override
    public List<Contrato> getAll(){
        return this.contratoRepo.findAll().stream().toList();
    }

    @Override
    public Optional<Contrato> getById(long id){
        return this.contratoRepo.findById(id);
    }

    @Override
    public List<Contrato> getAllByPessoaId(long pessoaId) {
        return this.contratoRepo.findAllByPessoaId(pessoaId);
    }



    
}
