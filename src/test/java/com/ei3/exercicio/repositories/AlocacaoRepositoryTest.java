package com.ei3.exercicio.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.AlocacaoId;
import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.repository.interfaces.AlocacaoRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilPessoaRepositoryJPA;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.ProjetoRepositoryJPA;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AlocacaoRepositoryTest {
    @Autowired
    private AlocacaoRepository alocacaoRepository;
    @Autowired
    private PerfilPessoaRepositoryJPA perfilPessoaRepositoryJPA;
    @Autowired
    private ProjetoRepositoryJPA projetoRepositoryJPA;

    private Projeto projetoEntity;
    private AlocacaoId alocacaoId;
    private Alocacao alocacaoEntity;

    private PerfilPessoa p_perfil;

    @BeforeEach
    public void setup(){
        p_perfil = this.perfilPessoaRepositoryJPA.findById(1L).get();
        projetoEntity = this.projetoRepositoryJPA.findById(1L).get();

        alocacaoId = new AlocacaoId(p_perfil.getId(), projetoEntity.getId());

        alocacaoEntity = new Alocacao(alocacaoId, 200);
        alocacaoEntity.setPessoa(p_perfil);
        alocacaoEntity.setProjeto(projetoEntity);
    }

    @Test
    public void shouldAdd(){
        this.alocacaoRepository.insert(alocacaoEntity);
        int actual = this.alocacaoRepository.all().size(); // meio que aqui eu já testo o all
        assertEquals(1, actual);
    }

    @Test
    public void gettingByProjectIdShouldReturnNotNullList(){
        Alocacao alocacao = this.alocacaoRepository.insert(alocacaoEntity);
        assertNotEquals(null, this.alocacaoRepository.findByProjetoId(alocacao.getProjeto().getId()));
    }

    @Test
    public void gettingByPerfilPessoaIdShouldReturnNotNullList(){
        Alocacao alocacao = this.alocacaoRepository.insert(alocacaoEntity);
        assertNotEquals(null, this.alocacaoRepository.findByPerfilPessoaId(alocacao.getPerfilPessoa().getId()));
    }

}
