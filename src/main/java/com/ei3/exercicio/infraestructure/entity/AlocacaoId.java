package com.ei3.exercicio.infraestructure.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AlocacaoId implements Serializable{
    private long idPessoa;
    private long idProjeto;

    public AlocacaoId(){}

    public AlocacaoId(long idPessoa, long idProjeto){
        this.idPessoa = idPessoa;
        this.idProjeto = idProjeto;
    }

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
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
        return Objects.equals(idPessoa, that.idPessoa) &&
               Objects.equals(idProjeto, that.idProjeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoa, idProjeto);
    }
}
