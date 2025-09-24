package com.ei3.exercicio.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.PessoaRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional

public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    private Pessoa pessoa;

    @BeforeEach
    public void setup() {
        pessoa = new Pessoa();
        pessoa.setNome("Pessoa Teste");
    }

@Test
public void shouldAddPessoa() {
    int before = pessoaRepository.getAll().size(); // tamanho antes

    pessoaRepository.insert(pessoa);

    var pessoas = pessoaRepository.getAll();
    int after = pessoas.size();

    assertEquals(before + 1, after); // aumentou em +1
    assertEquals("Pessoa Teste", pessoas.get(after - 1).getNome()); // último inserido
}

    @Test
    public void gettingAllShouldReturnNotNull() {
        pessoaRepository.insert(pessoa);
        assertNotNull(pessoaRepository.getAll());
    }

    @Test
    public void shouldGetPessoaById() {
        pessoaRepository.insert(pessoa);
        Optional<Pessoa> finded = pessoaRepository.getById(pessoa.getId());
        assertTrue(finded.isPresent());
        assertEquals("Pessoa Teste", finded.get().getNome());
    }
}
