package com.ei3.exercicio.infraestructure.entity;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min=2, max=75, message="O nome tem que ter entre 2 e 50 caracteres")
    private String nome;

    @NotNull
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Size(min=2, max=255, message="A descrição tem que ter entre 2 e 255 caracteres")
    private String descricao;
    private boolean is_active;

    
    public Projeto() {
    }

    public Projeto(long id,
            @NotBlank(message = "O nome não pode estar em branco") @Size(min = 2, max = 75, message = "O nome tem que ter entre 2 e 50 caracteres") String nome,
            @NotNull LocalDate dataInicio, LocalDate dataFim,
            @NotBlank(message = "A descrição não pode estar em branco") @Size(min = 2, max = 255, message = "A descrição tem que ter entre 2 e 255 caracteres") String descricao) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    public Projeto(long id,
            @NotBlank(message = "O nome não pode estar em branco") @Size(min = 2, max = 75, message = "O nome tem que ter entre 2 e 50 caracteres") String nome,
            @NotNull LocalDate dataInicio, LocalDate dataFim,
            @NotBlank(message = "A descrição não pode estar em branco") @Size(min = 2, max = 255, message = "A descrição tem que ter entre 2 e 255 caracteres") String descricao, boolean is_active) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.is_active = is_active;
    }

    public Projeto(
            @NotBlank(message = "O nome não pode estar em branco") @Size(min = 2, max = 75, message = "O nome tem que ter entre 2 e 50 caracteres") String nome,
            @NotNull LocalDate dataInicio, LocalDate dataFim,
            @NotBlank(message = "A descrição não pode estar em branco") @Size(min = 2, max = 255, message = "A descrição tem que ter entre 2 e 255 caracteres") String descricao) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
    

}
