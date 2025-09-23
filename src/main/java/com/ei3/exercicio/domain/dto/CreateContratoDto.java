package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

public record CreateContratoDto(long pessoaId, long perfilId, LocalDate dataInicio, LocalDate dataFim, int horasSemanais, double salarioHora){}
