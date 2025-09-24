package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.ei3.exercicio.infraestructure.entity.Contrato;

public interface ContratoRepository{
    public Contrato insert(Contrato contrato);
    public List<Contrato> getAll();
    public Optional<Contrato> getById(long id);
    public List<Contrato> getAllByPessoaId(long pessoaId);
    public List<Contrato> findAllByPerfilPessoaId(long perfilPessoaId);
}
