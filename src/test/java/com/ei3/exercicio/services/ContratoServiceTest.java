package com.ei3.exercicio.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ei3.exercicio.domain.dto.CreateContratoDto;
import com.ei3.exercicio.domain.service.ContratoImplService;

import jakarta.transaction.Transactional;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
public class ContratoServiceTest {
    
    @Autowired
    private ContratoImplService contratoService;

    @Test
    public void addingValidEntityshouldReturnTrue(){
        var contrato = new CreateContratoDto(1,1, LocalDate.now(), LocalDate.of(2026, 03, 29), 40, 35.2);
    
        assertTrue(this.contratoService.createContrato(contrato));
    }
    
    @Test
    public void addingInvalidEntityShouldReturnFalse(){
        var contrato = new CreateContratoDto(1,1, LocalDate.now(), LocalDate.of(2026, 03, 29), 43, 35.2) ;
        assertFalse(this.contratoService.createContrato(contrato));
    }

    @Test
    public void getAllShouldReturnNotEmptyList(){
        var contrato = new CreateContratoDto(1,1, LocalDate.now(), LocalDate.of(2026, 03, 29), 40, 35.2) ;
        this.contratoService.createContrato(contrato);
        assertNotEquals(true, this.contratoService.getAllContratos().isEmpty());
    }
    @Test // tentar criar contrato com um existente no banco e a data de inicio do novo ser antes da data de fim do existente
        // ex: inicio: 24/09/2025 --- fim 25/09/2025 => return false. 
    public void createContratoWithInvalidDateShouldReturnFalse(){
        var contrato1 = new CreateContratoDto(1,1, LocalDate.of(2025,9,22), LocalDate.of(2025, 9, 25), 40, 35.2);
        var contrato2 = new CreateContratoDto(1,2, LocalDate.of(2025, 9 ,24), LocalDate.of(2026, 03, 29), 40, 35.2);

        this.contratoService.createContrato(contrato1);
        assertFalse(this.contratoService.createContrato(contrato2));
    }

    @Test
    public void createContratoWithValiddDateShouldReturnTrue(){
        var contrato1 = new CreateContratoDto(1,1, LocalDate.of(2025,9,22), LocalDate.of(2025, 9, 25), 40, 35.2);
        var contrato2 = new CreateContratoDto(1,2, LocalDate.of(2025, 9 ,26), LocalDate.of(2026, 03, 29), 40, 35.2);
        this.contratoService.createContrato(contrato1);

        assertTrue(this.contratoService.createContrato(contrato2));
    }
}
