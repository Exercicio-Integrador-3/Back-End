package com.ei3.exercicio.infraestructure.repository.interfacesJPA;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ei3.exercicio.infraestructure.entity.Contrato;



public interface ContratoRepositoryJPA extends CrudRepository<Contrato, Long> {
    @Override
    public List<Contrato> findAll();
    public List<Contrato> findAllByPerfilPessoaId(long perfilPessoaId);
    // public List<Contrato> findAllByPessoaId(Long pessoaId);//tirar

    @Query("SELECT c FROM Contrato c JOIN c.perfilPessoa pp JOIN pp.pessoa p WHERE p.id = :pessoaId")
    List<Contrato> findAllByPessoaId(@Param("pessoaId") long pessoaId);
}
