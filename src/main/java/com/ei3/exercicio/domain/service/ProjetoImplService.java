package com.ei3.exercicio.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei3.exercicio.domain.dto.CreateProjetoDto;
import com.ei3.exercicio.domain.dto.ProjetoDto;
import com.ei3.exercicio.domain.service.interfaces.ProjetoService;
import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;
import com.ei3.exercicio.infraestructure.repository.interfaces.AlocacaoRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.ProjetoRepository;

@Service
public class ProjetoImplService implements ProjetoService{
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    public ProjetoImplService(){}

    public boolean createProject(CreateProjetoDto projetoDto){

        Projeto p = new Projeto(projetoDto.nome(), projetoDto.dataInicio(), projetoDto.dataFim(), projetoDto.descricao());

        this.projetoRepository.insert(p);

        return true;
    }

    public List<ProjetoDto> getAllProjetos(){
        var response = this.projetoRepository.getAll();
        for(Projeto p : response){
            var pps = this.alocacaoRepository.findByProjetoId(p.getId());
            if(estaAtivo(pps) && dataCorreta(p)){
                p.setIs_active(true);
                this.projetoRepository.updateStatus(p);
            }
        }
        
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

    private boolean estaAtivo(List<Alocacao> alocPorProjeto){
        List<TipoPerfil> allTipoPerfis = List.of(TipoPerfil.GERENTE, TipoPerfil.DEV, TipoPerfil.QA);
        List<TipoPerfil> listaToTipoPerfil = alocPorProjeto.stream().map(al -> al.getPerfilPessoa().getPerfil().getTipo()).toList();
        return listaToTipoPerfil.containsAll(allTipoPerfis);
    }

    private boolean dataCorreta(Projeto p){
        return (p.getDataInicio().isBefore(LocalDate.now()) || p.getDataInicio().isEqual(LocalDate.now())) && p.getDataFim().isAfter(LocalDate.now());
    }
}
