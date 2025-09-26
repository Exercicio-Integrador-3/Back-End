package com.ei3.exercicio.infraestructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;

    @ManyToOne()
    private PerfilPessoa perfilPessoa;
    
    @NotNull
    @FutureOrPresent(message = "Data de início tem que ser no mínimo hoje")
    private LocalDate dataInicio;
    
    @Future(message = "Data de fim precisa ser uma data no futuro e maior que hoje")
    private LocalDate dataFim;

    @NotNull
    @Max(40)
    @Positive(message = "Horas Semanais precisam ser um número positivo maior que 0")
    private int horasSemanais;

    @NotNull
    @Positive(message = "Salário precisa ser um número positivo maior que 0")
    private double salarioHora;

    public Contrato(PerfilPessoa perfilPessoa, @NotNull @FutureOrPresent(message = "Data de início tem que ser no mínimo hoje") LocalDate dataInicio, @Future(message = "Data de fim precisa ser uma data no futuro e maior que hoje") LocalDate dataFim,
            @NotNull @Size(max = 40) @Positive(message = "Horas Semanais precisam ser um número positivo maior que 0") int horasSemanais, @NotNull @Positive(message = "Salário precisa ser um número positivo maior que 0") double salarioHora) {
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
