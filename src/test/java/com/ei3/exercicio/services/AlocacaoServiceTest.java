package com.ei3.exercicio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

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

        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);
        idsPessoas.add(2L);
        idsPessoas.add(3L);
        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);
        idsPerfis.add(2L);
        idsPerfis.add(3L);


        var alocacao = new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 200);

        assertTrue(this.alocacaoService.createAlocacao(alocacao));
    }

    @Test
    public void addingInvalidEntityShouldReturnFalse(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(23198L);

        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);

        var alocacao = new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 200);

        assertFalse(this.alocacaoService.createAlocacao(alocacao));
    }

    
    @Test
    public void addingInvalidPerfilShouldReturnFalse(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);

        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1321L);

        var alocacao = new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 200);

        assertFalse(this.alocacaoService.createAlocacao(alocacao));
    }

    @Test
    public void addingPersonWhoIsAlreadyAllocatedWithDifferentProfilesReturnFalse(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);

        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);
        idsPerfis.add(2L);


        var alocacao1 = new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 200);

        assertFalse(this.alocacaoService.createAlocacao(alocacao1));
    }

    @Test
    public void addingPersonWhoIsAlreadyAllocatedWithSameProfileReturnFalse(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);

        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);
        idsPerfis.add(2L);

        var alocacao1 = new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 200);


        this.alocacaoService.createAlocacao(alocacao1);
        assertFalse(this.alocacaoService.createAlocacao(alocacao1));
    }

    @Test
    public void addingTwoPersonsWithSameProfileNotBeingGerenteReturnTrue(){

        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);
        idsPessoas.add(2L);


        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(2L);
        idsPerfis.add(2L);

        var alocacao1 = new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 200);

        assertTrue(this.alocacaoService.createAlocacao(alocacao1));
    }

    @Test
    public void addingTwoPersonsWithSameProfileBeingGerenteReturnFalse(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);
        idsPessoas.add(2L);


        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);
        idsPerfis.add(1L);

        var alocacao1 = new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 200);
        assertFalse(this.alocacaoService.createAlocacao(alocacao1));

    }

    @Test
    public void totalCostOfAProject(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);


        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);

        this.alocacaoService.createAlocacao(new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 120));

        assertEquals(8400.000, this.alocacaoService.custoTotal(1L));
    } 

    @Test
    public void costByPeriodOfTime(){      
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);


        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);

        this.alocacaoService.createAlocacao(new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 120));
        assertEquals(3360, this.alocacaoService.custoPeriodo(1,LocalDate.of(2025, 9, 24) , LocalDate.of(2025, 10, 1)));
    }

    @Test
    public void costByPeriodOfTimeOutOfTheTimeOfTheProject(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);


        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);

        this.alocacaoService.createAlocacao(new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 120));

        assertEquals(0, this.alocacaoService.custoPeriodo(1, LocalDate.of(2025, 10, 31), LocalDate.of(2025, 11, 5)));
    }

    @Test
    public void costByPeriodOfTimeBeforeTheTimeOfTheProject(){
        var idsPessoas = new ArrayList<Long>();
        idsPessoas.add(1L);


        var idsPerfis = new ArrayList<Long>();
        idsPerfis.add(1L);

        this.alocacaoService.createAlocacao(new CreateAlocacaoDto(idsPessoas, idsPerfis, 1, 120));

        assertEquals(0, this.alocacaoService.custoPeriodo(1, LocalDate.of(2024, 10, 31), LocalDate.of(2024, 11, 5)));

    }
}
