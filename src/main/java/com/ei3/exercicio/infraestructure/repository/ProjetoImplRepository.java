package com.ei3.exercicio.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.repository.interfaces.ProjetoRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.ProjetoRepositoryJPA;

@Repository
public class ProjetoImplRepository implements ProjetoRepository{
    
    @Autowired
    private ProjetoRepositoryJPA projetoRepo;

    public ProjetoImplRepository(){}

    @Override
    public Projeto insert(Projeto projeto){
        return this.projetoRepo.save(projeto);
    }

    @Override
    public List<Projeto> getAll(){
        return this.projetoRepo.findAll();
    }

    @Override
    public Optional<Projeto> getById(long id){
        return this.projetoRepo.findById(id);
    }

    public void updateStatus(Projeto projeto){
        this.projetoRepo.save(projeto);
    }
}
