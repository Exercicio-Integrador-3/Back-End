package com.ei3.exercicio.infraestructure.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotNull;

@Entity
public class Alocacao{
    
    @EmbeddedId
    private AlocacaoId Id;

    @MapsId("idPerfilPessoa")
    @ManyToOne
    private PerfilPessoa perfilPessoa;
    
    @MapsId("idProjeto")
    @ManyToOne
    private Projeto projeto;

    @NotNull
    private int quantidadeHoras;

    public Alocacao(){}

    public Alocacao(AlocacaoId id, @NotNull int quantidadeHoras) {
        Id = id;
        this.quantidadeHoras = quantidadeHoras;
    }

    public PerfilPessoa getPerfilPessoa() {
        return perfilPessoa;
    }

    public void setPessoa(PerfilPessoa perfilPessoa) {
        this.perfilPessoa = perfilPessoa;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public int getQuantidadeHoras() {
        return quantidadeHoras;
    }

    public void setQuantidadeHoras(int quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }
}