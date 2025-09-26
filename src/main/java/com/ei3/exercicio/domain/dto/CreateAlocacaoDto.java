package com.ei3.exercicio.domain.dto;

import java.util.List;

public record CreateAlocacaoDto(List<Long> idsPessoas, List<Long> idsPerfil, long idProjeto, int quantidadeHoras) {
    
}
