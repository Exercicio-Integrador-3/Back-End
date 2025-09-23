package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;

public interface PerfilRepository {
    public Perfil getByTipoPerfil(TipoPerfil tipoPerfil);
    public List<Perfil> getAllPerfis();
    public Optional<Perfil> getById(long id);
}
