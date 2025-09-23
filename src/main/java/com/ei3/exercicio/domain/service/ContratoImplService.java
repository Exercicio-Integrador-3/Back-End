package com.ei3.exercicio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei3.exercicio.domain.dto.ContratoDto;
import com.ei3.exercicio.domain.dto.CreateContratoDto;
import com.ei3.exercicio.domain.service.interfaces.ContratoService;
import com.ei3.exercicio.infraestructure.entity.Contrato;
import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.repository.interfaces.ContratoRepository;
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

    public boolean createContrato(CreateContratoDto contratoDto){
        var contratos = this.contratoRepository.getAllByPessoaId(contratoDto.pessoaId());
        if(!contratos.isEmpty()){
            boolean resp = contratos.stream().filter(c -> c.getDataFim().isAfter(contratoDto.dataInicio())).toList().isEmpty();
            if(!resp){
                return false;
            }
            
        }

        System.out.println("checando perfil:");


        Perfil perfil = perfilRepository.getById(contratoDto.perfilId()).orElse(null);
        //perfil inválido (inexistente)
        if(perfil == null){
            return false;   
        }
        System.out.println("checando pessoa:");

        Pessoa pessoa = pessoaRepository.getById(contratoDto.pessoaId()).orElse(null);
        // pessoa nao encontrada.
        if(pessoa==null){
            return false;
        }

        Contrato c = new Contrato(pessoa,
                     perfil, contratoDto.dataInicio(), contratoDto.dataFim(),
                     contratoDto.horasSemanais(), contratoDto.salarioHora());
        
        System.out.println("Inserindo: " + c.toString());
        
        try{
            System.out.println("Inserindo: " + c.toString());
            this.contratoRepository.insert(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<ContratoDto> getAllContratos() {
        return this.contratoRepository.getAll().stream().map(ContratoDto::fromModel).toList();
    }

    @Override
    public List<ContratoDto> getById(long id) {
        return this.contratoRepository.getAllByPessoaId(id).stream().map(ContratoDto::fromModel).toList();
    }

}
