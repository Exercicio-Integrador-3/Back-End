package com.ei3.exercicio.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;
import com.ei3.exercicio.infraestructure.repository.interfaces.PerfilPessoaRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilPessoaRepositoryJPA;

@Repository
public class PerfilPessoaImplRepository implements PerfilPessoaRepository{
    @Autowired
    private PerfilPessoaRepositoryJPA perfilPessoaRepositoryJPA;

    public PerfilPessoaImplRepository(){}

    public Optional<PerfilPessoa> findByPessoaId(long pessoaId){
        return this.perfilPessoaRepositoryJPA.findByPessoaId(pessoaId);
    }
    
    public Optional<PerfilPessoa> findById(long perfilPessoaId){
        return this.perfilPessoaRepositoryJPA.findById(perfilPessoaId);
    }

    public PerfilPessoa insert(PerfilPessoa perfilPessoa){
        return this.perfilPessoaRepositoryJPA.save(perfilPessoa);
    }

    public List<PerfilPessoa> findAllByPessoaId(long pessoaId){
        return this.perfilPessoaRepositoryJPA.findAll().stream().filter(pp -> pp.getPessoa().getId() == pessoaId).toList();
    }

    @Override
    public Optional<PerfilPessoa> findByPessoaIdAndTipoPerfil(long pessoaId, TipoPerfil tipoPerfil) {
        return this.perfilPessoaRepositoryJPA.findAll().stream().filter(pp-> pp.getPerfil().getTipo() == tipoPerfil).findFirst();
    }
}
