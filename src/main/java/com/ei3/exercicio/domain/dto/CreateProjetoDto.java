package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

public record CreateProjetoDto(String nome, LocalDate dataInicio, LocalDate dataFim, String descricao) {}
