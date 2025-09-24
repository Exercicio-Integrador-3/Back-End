package com.ei3.exercicio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ei3.exercicio.domain.dto.CreatePessoaDto;
import com.ei3.exercicio.domain.service.PessoaImplService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
public class PessoaServiceTest {

    @Autowired
    private PessoaImplService pessoaService;

    @Test
    public void addingValidPessoaShouldReturnTrue() {
        var pessoaDto = new CreatePessoaDto("Pessoa Teste");
        boolean created = this.pessoaService.createPessoa(pessoaDto);
        assertTrue(created);
    }

    @Test
    public void getAllShouldReturnNotEmptyList() {
        var pessoaDto = new CreatePessoaDto("Pessoa Teste 2");
        this.pessoaService.createPessoa(pessoaDto);
        var pessoas = this.pessoaService.getAllPessoas();
        assertNotEquals(true, pessoas.isEmpty());
    }

    @Test
    public void getPessoaByIdShouldReturnCorrectPessoa() {
        var pessoaDto = new CreatePessoaDto("Pessoa Teste 3");
        this.pessoaService.createPessoa(pessoaDto);
        var pessoas = this.pessoaService.getAllPessoas();
        long id = pessoas.get(pessoas.size() - 1).id(); // pega o último cadastrado
        Optional<?> found = this.pessoaService.getPessoaById(id);
        assertTrue(found.isPresent());
        assertEquals("Pessoa Teste 3", ((com.ei3.exercicio.domain.dto.PessoaDto) found.get()).nome());
    }
}
