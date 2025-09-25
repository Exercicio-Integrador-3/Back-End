package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

import com.ei3.exercicio.infraestructure.entity.Contrato;

public record CreateContratoDto(long pessoaId, long perfilId, LocalDate dataInicio, LocalDate dataFim, int horasSemanais, double salarioHora){
    public static CreateContratoDto fromModel(Contrato c){
        return new CreateContratoDto(c.getPerfilPessoa().getPerfil().getId(), c.getPerfilPessoa().getPessoa().getId(), c.getDataInicio(), c.getDataFim(), c.getHorasSemanais(), c.getSalarioHora());
    }
}
