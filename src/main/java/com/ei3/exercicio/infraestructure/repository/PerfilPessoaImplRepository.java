package com.ei3.exercicio.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.PerfilPessoaRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilPessoaRepositoryJPA;

@Repository
public class PerfilPessoaImplRepository implements PerfilPessoaRepository{
    @Autowired
    private PerfilPessoaRepositoryJPA perfilPessoaRepositoryJPA;

    public PerfilPessoaImplRepository(){}

    public List<PerfilPessoa> findByPessoaId(long pessoaId){
        return this.perfilPessoaRepositoryJPA.findByPessoaId(pessoaId);
    }
    
    public Optional<PerfilPessoa> findById(long perfilPessoaId){
        return this.perfilPessoaRepositoryJPA.findById(perfilPessoaId);
    }

    public PerfilPessoa insert(PerfilPessoa perfilPessoa){
        return this.perfilPessoaRepositoryJPA.save(perfilPessoa);
    }
}
