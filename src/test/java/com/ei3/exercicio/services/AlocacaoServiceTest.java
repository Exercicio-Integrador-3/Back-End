package com.ei3.exercicio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
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

    // @Test
    // public void addingInvalidEntityShouldReturnFalse(){
    //     var alocacao = new CreateAlocacaoDto(23478, 1, 1, 200);

    //     assertFalse(this.alocacaoService.createAlocacao(alocacao));
    // }

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

    // @Test
    // public void addingPersonWhoIsAlreadyAllocatedWithSameProfileReturnFalse(){
    //     var alocacao1 = new CreateAlocacaoDto(1, 1, 1, 200);
    //     var alocacao2 = new CreateAlocacaoDto(1, 1, 1, 200);

    //     this.alocacaoService.createAlocacao(alocacao1);
    //     assertFalse(this.alocacaoService.createAlocacao(alocacao2));
    // }

    // @Test
    // public void addingTwoPersonsWithDifferentProfilesReturnTrue(){
    //     var alocacao1 = new CreateAlocacaoDto(1, 1, 1, 200);
    //     var alocacao2 = new CreateAlocacaoDto(2, 2, 1, 200);

    //     this.alocacaoService.createAlocacao(alocacao1);
    //     assertTrue(this.alocacaoService.createAlocacao(alocacao2));
    // }

    // @Test
    // public void addingTwoPersonsWithSameProfileNotBeingGerenteReturnTrue(){
    //     var alocacao1 = new CreateAlocacaoDto(1, 2, 1, 200);
    //     var alocacao2 = new CreateAlocacaoDto(2, 2, 1, 200);

    //     this.alocacaoService.createAlocacao(alocacao1);
    //     assertTrue(this.alocacaoService.createAlocacao(alocacao2));  
    // }

    // @Test
    // public void addingTwoPersonsWithSameProfileBeingGerenteReturnFalse(){
    //     var alocacao1 = new CreateAlocacaoDto(1, 1, 1, 200);
    //     var alocacao2 = new CreateAlocacaoDto(2, 1, 1, 200);

    //     this.alocacaoService.createAlocacao(alocacao1);
    //     assertFalse(this.alocacaoService.createAlocacao(alocacao2));
    // }

    // @Test
    // public void totalCostOfAProject(){
    //     this.alocacaoService.createAlocacao(new CreateAlocacaoDto(1, 1, 1, 120));

    //     assertEquals(8400.000, this.alocacaoService.custoTotal(1L));
    // } 

    // @Test
    // public void costByPeriodOfTime(){      
    //     this.alocacaoService.createAlocacao(new CreateAlocacaoDto(1, 1, 1, 120));

    //     assertEquals(3360, this.alocacaoService.custoPeriodo(1,LocalDate.of(2025, 9, 24) , LocalDate.of(2025, 10, 1)));
    // }

    // @Test
    // public void costByPeriodOfTimeOutOfTheTimeOfTheProject(){
    //     this.alocacaoService.createAlocacao(new CreateAlocacaoDto(1, 1, 1, 120));

    //     assertEquals(0, this.alocacaoService.custoPeriodo(1, LocalDate.of(2025, 10, 31), LocalDate.of(2025, 11, 5)));
    // }

    // @Test
    // public void costByPeriodOfTimeBeforeTheTimeOfTheProject(){
    //     this.alocacaoService.createAlocacao(new CreateAlocacaoDto(1, 1, 1, 120));

    //     assertEquals(0, this.alocacaoService.custoPeriodo(1, LocalDate.of(2024, 10, 31), LocalDate.of(2024, 11, 5)));
    // }

}
