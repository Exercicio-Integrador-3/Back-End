package com.ei3.exercicio.infraestructure.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Persistent;
import org.springframework.validation.annotation.Validated;

import jakarta.persistence.CascadeType;
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
    // @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;


    @ManyToOne()
    // @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @NotNull
    private LocalDate dataInicio;
    
    private LocalDate dataFim;

    @NotNull
    @Max(40)
    private int horasSemanais;

    @NotNull
    private double salarioHora;

    public Contrato(Pessoa pessoa, Perfil perfil, @NotNull LocalDate dataInicio, LocalDate dataFim,
            @NotNull @Size(max = 40) int horasSemanais, @NotNull double salarioHora) {
        this.pessoa = pessoa;
        this.perfil = perfil;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horasSemanais = horasSemanais;
        this.salarioHora = salarioHora;
    }

    public Contrato(long id, Pessoa pessoa, Perfil perfil, LocalDate dataInicio, LocalDate dataFim,
            int horasSemanais, double salarioHora) {
        Id = id;
        this.pessoa = pessoa;
        this.perfil = perfil;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
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
