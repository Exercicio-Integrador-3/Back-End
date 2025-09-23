package com.ei3.exercicio.infraestructure.repository.interfacesJPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ei3.exercicio.infraestructure.entity.Projeto;

public interface ProjetoRepositoryJPA extends JpaRepository<Projeto, Long>{
    
}
