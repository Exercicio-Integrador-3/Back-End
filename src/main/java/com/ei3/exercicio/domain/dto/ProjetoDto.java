package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

public record ProjetoDto(long id, String nome, LocalDate dataInicio, LocalDate dataFim, String descricao) {}
