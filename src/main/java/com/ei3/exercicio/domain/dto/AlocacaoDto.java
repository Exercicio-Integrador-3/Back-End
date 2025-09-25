package com.ei3.exercicio.domain.dto;

import com.ei3.exercicio.infraestructure.entity.Alocacao;

public record AlocacaoDto(String nome, String funcao, long idPerfilPessoa, long idProjeto, String nomeProjeto, int quantidadeHoras) {
    public static AlocacaoDto fromModel(Alocacao a){
        return new AlocacaoDto(
            a.getPerfilPessoa().getPessoa().getNome(),
            a.getPerfilPessoa().getPerfil().getTipo().toString(),
            a.getPerfilPessoa().getId(), 
            a.getProjeto().getId(),
            a.getProjeto().getNome(),
            a.getQuantidadeHoras());
    }
}
