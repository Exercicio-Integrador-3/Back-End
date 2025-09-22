package com.ei3.exercicio.domain.dto;

import java.time.LocalDate;

public class ContratoDto {
    
    public long Id;
    private long pessoaId;
    private long perfilId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int horasSemanais;
    private double salarioHora;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public long getPerfilId() {
        return this.perfilId;
    }

    public void setPerfilId(long perfil) {
        this.perfilId = perfil;
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

    public int getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(int horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        this.salarioHora = salarioHora;
    }



}
