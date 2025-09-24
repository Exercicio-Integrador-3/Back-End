package com.ei3.exercicio.infraestructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;

    @ManyToOne()
    private PerfilPessoa perfilPessoa;
    
    @NotNull
    private LocalDate dataInicio;
    
    private LocalDate dataFim;

    @NotNull
    @Max(40)
    private int horasSemanais;

    @NotNull
    private double salarioHora;

    public Contrato(PerfilPessoa perfilPessoa, @NotNull LocalDate dataInicio, LocalDate dataFim,
            @NotNull @Size(max = 40) int horasSemanais, @NotNull double salarioHora) {
        this.perfilPessoa = perfilPessoa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horasSemanais = horasSemanais;
        this.salarioHora = salarioHora;
    }

    public Contrato(long id, PerfilPessoa perfilPessoa, LocalDate dataInicio, LocalDate dataFim,
            int horasSemanais, double salarioHora) {
        Id = id;
        this.perfilPessoa = perfilPessoa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horasSemanais = horasSemanais;
        this.salarioHora = salarioHora;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public PerfilPessoa getPerfilPessoa() {
        return perfilPessoa;
    }

    public void setPerfilPessoa(PerfilPessoa perfilPessoa) {
        this.perfilPessoa = perfilPessoa;
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

    public Contrato(){}
}
