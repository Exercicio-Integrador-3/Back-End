package com.ei3.exercicio.infraestructure.repository.interfacesJPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.AlocacaoId;

public interface AlocacaoRepositoryJPA extends JpaRepository<Alocacao, AlocacaoId>{
    List<Alocacao> findByProjetoId(Long projetoId);
    List<Alocacao> findByPerfilPessoaId(Long perfilPessoaId);
}
