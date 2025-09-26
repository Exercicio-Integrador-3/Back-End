package com.ei3.exercicio.services;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.ei3.exercicio.domain.dto.CreateProjetoDto;
import com.ei3.exercicio.domain.service.ProjetoImplService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Sql("/test-projectservice-alocacoes.sql")
public class ProjetoServiceTest {
    
    @Autowired
    private ProjetoImplService projetoService;

    @Test
    public void addingValidEntityShouldReturnTrue(){
        var projeto = new CreateProjetoDto("Projeto Teste", LocalDate.now(), LocalDate.of(2025, 10, 23), "Descrição de projeto teste");

        assertTrue(this.projetoService.createProject(projeto));
    }

    @Test
    public void getAllShouldReturnNotEmptyList(){
        var projeto = new CreateProjetoDto("Projeto Teste", LocalDate.now(), LocalDate.of(2025, 10, 23), "Descrição de projeto teste");

        this.projetoService.createProject(projeto);
        assertNotEquals(true, projetoService.getAllProjetos().isEmpty());
    }

    @Test
    public void getProjectByIdShouldReturnNotEmpty(){
        var projeto = new CreateProjetoDto("Projeto Teste", LocalDate.now(), LocalDate.of(2025, 10, 23), "Descrição de projeto teste");

        this.projetoService.createProject(projeto);

        assertNotEquals(true, projetoService.getProjetoById(1L));
    }

    @Test
    public void getAllProjectsOneShouldBeActive(){

        var resposta = this.projetoService.getAllProjetos();



        boolean actual = resposta.stream().anyMatch(r-> r.is_active());
        assertTrue(actual);
    }
}
