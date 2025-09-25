package com.ei3.exercicio.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei3.exercicio.domain.dto.ContratoDto;
import com.ei3.exercicio.domain.dto.CreateContratoDto;
import com.ei3.exercicio.domain.service.interfaces.ContratoService;
import com.ei3.exercicio.infraestructure.entity.Contrato;
import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.ContratoRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.PerfilPessoaRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.PerfilRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.PessoaRepository;

@Service
public class ContratoImplService implements ContratoService{
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PerfilPessoaRepository perfilPessoaRepository;

    public boolean createContrato(CreateContratoDto contratoDto){
        Perfil perfil = perfilRepository.getById(contratoDto.perfilId()).orElse(null);
        //perfil inválido (inexistente)
        if(perfil == null){
            return false;   
        }

        Pessoa pessoa = pessoaRepository.getById(contratoDto.pessoaId()).orElse(null);
        // pessoa nao encontrada.
        if(pessoa==null){
            return false;
        }

        Optional<PerfilPessoa> perfilPessoa = perfilPessoaRepository.findByPessoaId(pessoa.getId()).filter(pp -> pp.getPerfil().getId() == perfil.getId()).stream().findFirst();
        //1. acha pelo id da pessoa, 2. filtra perfilPessoa e vê se já existe algum perfil com essa pessoa, 3.pega o primeiro    
        PerfilPessoa ppNovo; 

        if(perfilPessoa.isPresent()){ //perfil pessoa existe um ou mais
            var contratos = this.contratoRepository.findAllByPerfilPessoaId(perfilPessoa.get().getId()); //contratos pelo id do perfil da pessoa
            if(!contratos.isEmpty()){
                boolean resp = contratos.stream().filter(c -> c.getDataFim().isAfter(contratoDto.dataInicio())).toList().isEmpty(); //verifica se tem algum contrato nesse período (ativo)
                if(!resp){
                    return false;
                }
            }

            Contrato c = new Contrato(perfilPessoa.get(), contratoDto.dataInicio(), contratoDto.dataFim(),
                     contratoDto.horasSemanais(), contratoDto.salarioHora()); //caso não tenha pode criar um
            
            try{
                this.contratoRepository.insert(c);
            }catch(Exception e){
                return false;
            }
            
        } else {
            var contratos = this.contratoRepository.getAllByPessoaId(pessoa.getId()); //contratos pelo id da pessoa
            if(!contratos.isEmpty()){
                boolean resp = contratos.stream().filter(c -> c.getDataFim().isAfter(contratoDto.dataInicio())).toList().isEmpty(); //verifica se tem algum contrato nesse período (ativo)
                if(!resp){
                    return false;
                }
            }
            ppNovo = new PerfilPessoa(pessoa, perfil); // caso a pessoa nao tenha um contrato ativo && a junção do perfil e da pessoa não existam cria uma nova 
            perfilPessoaRepository.insert(ppNovo);
            Contrato c = new Contrato(ppNovo, contratoDto.dataInicio(), contratoDto.dataFim(), // faz um contrato com esse novo perfilPessoa
                     contratoDto.horasSemanais(), contratoDto.salarioHora());
        
            try{
                this.contratoRepository.insert(c);
            }catch(Exception e){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ContratoDto> getAllContratos() {
        return this.contratoRepository.getAll().stream().map(ContratoDto::fromModel).toList();
    }

    @Override
    public List<ContratoDto> getContratosByPessoaId(long id) {
        return this.contratoRepository.getAllByPessoaId(id).stream().map(ContratoDto::fromModel).toList();
    }

}
