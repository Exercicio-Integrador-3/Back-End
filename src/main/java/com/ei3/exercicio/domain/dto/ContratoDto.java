package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

import com.ei3.exercicio.infraestructure.entity.Contrato;

public record ContratoDto(long id, String nome, String funcao, long perfilPessoaId, LocalDate dataInicio, LocalDate dataFim, int horasSemanais, double salarioHora, String nomePessoa){
    public static ContratoDto fromModel(Contrato c){
        return new ContratoDto(
        c.getId(),
        c.getPerfilPessoa().getPessoa().getNome(),
        c.getPerfilPessoa().getPerfil().getTipo().toString(), 
        c.getPerfilPessoa().getId(),
        c.getDataInicio(),
        c.getDataFim(),
        c.getHorasSemanais(),
        c.getSalarioHora(),
        c.getPerfilPessoa().getPessoa().getNome());
    }
}

