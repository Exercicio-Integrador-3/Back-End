package com.ei3.exercicio.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;
import com.ei3.exercicio.infraestructure.repository.interfaces.PerfilRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilRepositoryJPA;

@Repository
public class PerfilImplRepository implements PerfilRepository {

    @Autowired
    private PerfilRepositoryJPA perfilRepo;

    @Override
    public Perfil getByTipoPerfil(TipoPerfil tipoPerfil) {
        return this.perfilRepo.findByTipo(tipoPerfil);
    }

    @Override
    public List<Perfil> getAllPerfis() {
        return this.perfilRepo.findAll().stream().toList();
    }

    @Override
    public Optional<Perfil> getById(long id) {
        return this.perfilRepo.findById(id);
    }
    
}
