package com.ei3.exercicio.infraestructure.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AlocacaoId implements Serializable{
    private long idPerfilPessoa;
    private long idProjeto;

    public AlocacaoId(){}

    public AlocacaoId(long idPerfilPessoa, long idProjeto){
        this.idPerfilPessoa = idPerfilPessoa;
        this.idProjeto = idProjeto;
    }

    public long getIdPerfilPessoa() {
        return idPerfilPessoa;
    }

    public void setIdPerfilPessoa(long idPerfilPessoa) {
        this.idPerfilPessoa = idPerfilPessoa;
    }

    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlocacaoId that)) return false;
        return Objects.equals(idPerfilPessoa, that.idPerfilPessoa) &&
               Objects.equals(idProjeto, that.idProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerfilPessoa, idProjeto);
    }
}
