package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

import com.ei3.exercicio.infraestructure.entity.Projeto;

public record ProjetoDto(long id, String nome, LocalDate dataInicio, LocalDate dataFim, String descricao, boolean is_active) {
    public static ProjetoDto fromModel(Projeto p){
        return new ProjetoDto(
            p.getId(),
            p.getNome(),
            p.getDataInicio(),
            p.getDataFim(),
            p.getDescricao(),
            p.getIs_active());
    }
}
