package com.ei3.exercicio.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.repository.interfaces.ProjetoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProjetoRepositoryTest {
    
    @Autowired
    private ProjetoRepository projetoRepository;

    private Projeto projetoEntity;

    @Autowired
    private Validator validator;

    @BeforeEach
    public void setup(){
        projetoEntity = new Projeto("Projeto de teste", LocalDate.now(), LocalDate.of(2025, 10, 23), "Descrição do projeto de teste");
    }

    @Test
    public void shouldAdd(){
        this.projetoRepository.insert(projetoEntity);
        int actual = this.projetoRepository.getAll().size();
        assertEquals(2, actual); //já tem um projeto no banco
    }

    @Test
    public void gettingAllShouldNotReturnNull(){
        this.projetoRepository.insert(projetoEntity);
        assertNotEquals(null, projetoRepository.getAll());
    }

    @Test
    public void gettingProjectByIdShouldNotReturnNull(){
        Projeto p = this.projetoRepository.insert(projetoEntity);

        var findingProject = this.projetoRepository.getById(p.getId());
        assertNotEquals(null, findingProject);
    }

    @Test
    public void passingWrongFieldsShouldThrowAnError(){
        this.projetoEntity.setNome(null);

        assertThrowsExactly(ConstraintViolationException.class, () -> this.projetoRepository.insert(projetoEntity));
    }
}
