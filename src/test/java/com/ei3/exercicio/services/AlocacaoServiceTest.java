package com.ei3.exercicio.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ei3.exercicio.domain.dto.CreateAlocacaoDto;
import com.ei3.exercicio.domain.service.AlocacaoImplService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AlocacaoServiceTest {
    
    @Autowired
    private AlocacaoImplService alocacaoService;

    @Test
    public void addingValidEntityshouldReturnTrue(){
        var alocacao = new CreateAlocacaoDto(1, 1, 1, 200);

        assertTrue(this.alocacaoService.createAlocacao(alocacao));
    }

    @Test
    public void addingInvalidEntityShouldReturnFalse(){
        var alocacao = new CreateAlocacaoDto(23478, 1, 1, 200);

        assertFalse(this.alocacaoService.createAlocacao(alocacao));
    }

    @Test
    public void addingPersonWhoIsAlreadyAllocatedWithDifferentProfilesReturnFalse(){
        var alocacao1 = new CreateAlocacaoDto(1, 1, 1, 200);
        var alocacao2 = new CreateAlocacaoDto(1, 2, 1, 200);

        this.alocacaoService.createAlocacao(alocacao1);
        assertFalse(this.alocacaoService.createAlocacao(alocacao2));
    }

    @Test
    public void addingPersonWhoIsAlreadyAllocatedWithSameProfileReturnFalse(){
        var alocacao1 = new CreateAlocacaoDto(1, 1, 1, 200);
        var alocacao2 = new CreateAlocacaoDto(1, 1, 1, 200);

        this.alocacaoService.createAlocacao(alocacao1);
        assertFalse(this.alocacaoService.createAlocacao(alocacao2));
    }

    @Test
    public void addingTwoPersonsWithDifferentProfilesReturnTrue(){
        var alocacao1 = new CreateAlocacaoDto(1, 1, 1, 200);
        var alocacao2 = new CreateAlocacaoDto(2, 2, 1, 200);

        this.alocacaoService.createAlocacao(alocacao1);
        assertTrue(this.alocacaoService.createAlocacao(alocacao2));
    }

    @Test
    public void addingTwoPersonsWithSameProfileNotBeingGerenteReturnTrue(){
        var alocacao1 = new CreateAlocacaoDto(1, 2, 1, 200);
        var alocacao2 = new CreateAlocacaoDto(2, 2, 1, 200);

        this.alocacaoService.createAlocacao(alocacao1);
        assertTrue(this.alocacaoService.createAlocacao(alocacao2));  
    }

    @Test
    public void addingTwoPersonsWithSameProfileBeingGerenteReturnFalse(){
        var alocacao1 = new CreateAlocacaoDto(1, 1, 1, 200);
        var alocacao2 = new CreateAlocacaoDto(2, 1, 1, 200);

        this.alocacaoService.createAlocacao(alocacao1);
        assertFalse(this.alocacaoService.createAlocacao(alocacao2));
    }

    // Depois de melhorar a lógica de custo, implementar testes
}
