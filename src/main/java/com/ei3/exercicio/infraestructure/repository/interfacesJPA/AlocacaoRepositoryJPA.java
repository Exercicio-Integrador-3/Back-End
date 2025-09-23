package com.ei3.exercicio.infraestructure.repository.interfacesJPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.AlocacaoId;

public interface AlocacaoRepositoryJPA extends JpaRepository<Alocacao, AlocacaoId>{

}
