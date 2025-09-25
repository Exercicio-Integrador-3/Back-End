package com.ei3.exercicio.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import com.ei3.exercicio.infraestructure.entity.Contrato;
import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.ContratoRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilPessoaRepositoryJPA;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ContratoRepositoryTest {
    
    @Autowired
    private ContratoRepository contratoRepository;

    private Contrato contratoEntity; 
    
    @Autowired
    private Validator validator;

    @Autowired
    private PerfilPessoaRepositoryJPA perfilPessoaRepositoryJPA;

    private PerfilPessoa p_perfil;

    @BeforeEach
    public void setup(){
        p_perfil = this.perfilPessoaRepositoryJPA.findById(1L).get();
        contratoEntity = new Contrato(p_perfil, LocalDate.now(), LocalDate.of(2025, 12, 30), 40, 35.3);
    }

    @Test
    public void shouldAdd(){
        this.contratoRepository.insert(contratoEntity);
        int actual = this.contratoRepository.getAll().size();
        assertEquals(2, actual); //já temos um contrato no banco

    }
    
    @Test
    public void gettingAllShouldNotReturnNull(){
        this.contratoRepository.insert(contratoEntity);
        assertNotEquals(null, contratoRepository.getAll());
        }

    @Test
    public void gettingAllByPessoaIdShouldReturnNotNullList(){
        Contrato contrato = this.contratoRepository.insert(contratoEntity);
        long pessoaId = this.contratoRepository.getById(contrato.Id).get().getPerfilPessoa().getPessoa().getId();
        assertNotEquals(null, contratoRepository.getAllByPessoaId(pessoaId));
    }

    @Test
    public void addingAnInvalidContratoShouldNotAdd(){
        contratoEntity.setHorasSemanais(45);
        Set<ConstraintViolation<Contrato>> violations = validator.validate(contratoEntity);

        assertFalse(violations.isEmpty());
    }
}
