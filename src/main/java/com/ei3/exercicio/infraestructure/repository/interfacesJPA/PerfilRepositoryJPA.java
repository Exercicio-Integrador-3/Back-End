package com.ei3.exercicio.infraestructure.repository.interfacesJPA;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;


public interface PerfilRepositoryJPA extends CrudRepository<Perfil, Long>{
    public Perfil findByTipo(TipoPerfil tipo);
    public List<Perfil> findAll();
}
