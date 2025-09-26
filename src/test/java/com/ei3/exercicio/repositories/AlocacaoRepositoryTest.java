package com.ei3.exercicio.repositories;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.AlocacaoId;
import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;
import com.ei3.exercicio.infraestructure.repository.interfaces.AlocacaoRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilPessoaRepositoryJPA;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilRepositoryJPA;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PessoaRepositoryJPA;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.ProjetoRepositoryJPA;

import jakarta.transaction.Transactional;

@SpringBootTest(properties ="spring.sql.init.mode=never")
@Transactional
public class AlocacaoRepositoryTest {
    @Autowired
    private AlocacaoRepository alocacaoRepository;
    @Autowired
    private PerfilPessoaRepositoryJPA perfilPessoaRepositoryJPA;
    @Autowired
    private ProjetoRepositoryJPA projetoRepositoryJPA;
    @Autowired
    private PerfilRepositoryJPA perfilRepository;
    @Autowired
    private PessoaRepositoryJPA pessoaRepository;

    private Projeto projetoEntity;
    private AlocacaoId alocacaoId;
    private Alocacao alocacaoEntity;

    private PerfilPessoa p_perfil;

    @BeforeEach
    public void setup(){
        Pessoa p = new Pessoa("Gustavo Teixeira");
        pessoaRepository.save(p);
        Perfil perfil = new Perfil();
        perfil.setTipo(TipoPerfil.DEV);
        perfilRepository.save(perfil);

        p_perfil = new PerfilPessoa(p, perfil);
        p_perfil = perfilPessoaRepositoryJPA.save(p_perfil);
        Projeto projeto = new Projeto("Projeto Alpha", LocalDate.of(2025, 8,20), LocalDate.of(2025,10,25), "Nada");
        
        projetoEntity = this.projetoRepositoryJPA.save(projeto);


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
