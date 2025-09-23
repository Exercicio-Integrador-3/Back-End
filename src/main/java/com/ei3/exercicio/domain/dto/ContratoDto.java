package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

import com.ei3.exercicio.infraestructure.entity.Contrato;

public record ContratoDto(long id, long pessoaId, long perfilId, LocalDate dataInicio, LocalDate dataFim, int horasSemanais, double salarioHora, String nomePessoa){
    public static ContratoDto fromModel(Contrato c){
        return new ContratoDto(c.getId(),c.getPessoa().getId(),
        c.getPerfil().getId(),c.getDataInicio(),c.getDataFim(),c.getHorasSemanais(),c.getSalarioHora(),c.getPessoa().getNome());
    }
}

