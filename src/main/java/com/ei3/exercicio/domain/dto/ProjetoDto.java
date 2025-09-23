package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

public class ProjetoDto {
    private long Id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }
    public ProjetoDto(long id, String nome, LocalDate dataInicio, LocalDate dataFim, String descricao) {
        Id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    public ProjetoDto(){}
}
