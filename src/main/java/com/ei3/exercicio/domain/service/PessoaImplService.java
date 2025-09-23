package com.ei3.exercicio.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ei3.exercicio.domain.dto.CreatePessoaDto;
import com.ei3.exercicio.domain.dto.PessoaDto;
import com.ei3.exercicio.domain.service.interfaces.PessoaService;
import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.PessoaRepository;

@Service
public class PessoaImplService implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaImplService(){}

    @Override
    public boolean createPessoa(CreatePessoaDto pessoaDto) {
        Pessoa p = new Pessoa();
        p.setNome(pessoaDto.nome());

        this.pessoaRepository.insert(p);

        return true;
    }

    @Override
    public List<PessoaDto> getAllPessoas() {
        return this.pessoaRepository.getAll()
            .stream()
            .map(p -> new PessoaDto(p.getId(), p.getNome()))
            .toList();
    }

    @Override
    public Optional<PessoaDto> getPessoaById(long id) {
        return this.pessoaRepository.getById(id)
            .map(p -> new PessoaDto(p.getId(), p.getNome()));
    }
}
