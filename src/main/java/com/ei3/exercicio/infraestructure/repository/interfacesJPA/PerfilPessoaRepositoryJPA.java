package com.ei3.exercicio.infraestructure.repository.interfacesJPA;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;


public interface PerfilPessoaRepositoryJPA extends JpaRepository<PerfilPessoa, Long>{
    Optional<PerfilPessoa> findByPessoaId(Long pessoaId);
} 