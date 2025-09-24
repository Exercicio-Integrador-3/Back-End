package com.ei3.exercicio.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.PessoaRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PessoaRepositoryJPA;

@Repository
public class PessoaImplRepository implements PessoaRepository {

    @Autowired
    private PessoaRepositoryJPA pessoaRepo;

    public PessoaImplRepository(){}

    @Override
    public void insert(Pessoa pessoa){
        this.pessoaRepo.save(pessoa);
    }

    @Override
    public List<Pessoa> getAll(){
        return this.pessoaRepo.findAll();
    }
    @Override
    public List<Long> getAllIds() {
    return getAll().stream()
                   .map(Pessoa::getId)
                   .toList();
}


    @Override
    public Optional<Pessoa> getById(long id){
        return this.pessoaRepo.findById(id);
    }
}