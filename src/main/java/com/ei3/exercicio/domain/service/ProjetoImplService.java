package com.ei3.exercicio.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei3.exercicio.domain.dto.CreateProjetoDto;
import com.ei3.exercicio.domain.dto.ProjetoDto;
import com.ei3.exercicio.domain.service.interfaces.ProjetoService;
import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.repository.interfaces.ProjetoRepository;

@Service
public class ProjetoImplService implements ProjetoService{
    @Autowired
    private ProjetoRepository projetoRepository;

    public ProjetoImplService(){}

    public boolean createProject(CreateProjetoDto projetoDto){

        Projeto p = new Projeto(projetoDto.nome(), projetoDto.dataInicio(), projetoDto.dataFim(), projetoDto.descricao());

        this.projetoRepository.insert(p);

        return true;
    }

    public List<ProjetoDto> getAllProjetos(){
        return this.projetoRepository.getAll()
        .stream()
        .map(ProjetoDto::fromModel)
        .toList();
    }

    @Override
    public List<Long> getAllIds() {
         return projetoRepository.getAll()
        .stream()
        .map(Projeto::getId)
        .toList();
    }

    public Optional<ProjetoDto> getProjetoById(long id){
        return this.projetoRepository.getById(id)
        .map(ProjetoDto::fromModel);
    }
}
