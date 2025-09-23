package com.ei3.exercicio.infraestructure.repository.interfacesJPA;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ei3.exercicio.infraestructure.entity.Contrato;



public interface ContratoRepositoryJPA extends CrudRepository<Contrato, Long> {
    @Override
    public List<Contrato> findAll();
    public List<Contrato> findAllByPessoaId(long pessoaId);
}
