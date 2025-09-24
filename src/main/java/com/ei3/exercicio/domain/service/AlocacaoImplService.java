package com.ei3.exercicio.domain.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei3.exercicio.domain.dto.AlocacaoDto;
import com.ei3.exercicio.domain.dto.CreateAlocacaoDto;
import com.ei3.exercicio.domain.service.interfaces.AlocacaoService;
import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.AlocacaoId;
import com.ei3.exercicio.infraestructure.entity.Contrato;
import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.repository.interfaces.AlocacaoRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.ContratoRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.ProjetoRepository;
import com.ei3.exercicio.infraestructure.repository.interfacesJPA.PerfilPessoaRepositoryJPA;

@Service
public class AlocacaoImplService implements AlocacaoService{
    
    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PerfilPessoaRepositoryJPA perfilPessoaRepository;

    public AlocacaoImplService(){}

    public boolean createAlocacao(CreateAlocacaoDto alocacaoDto){
        var perfilPessoa = perfilPessoaRepository.findByPessoaId(alocacaoDto.idPessoa()).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        var projeto = projetoRepository.getById(alocacaoDto.idProjeto()).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        AlocacaoId aId = new AlocacaoId(alocacaoDto.idPessoa(), alocacaoDto.idProjeto());
        Alocacao a = new Alocacao(aId, alocacaoDto.quantidadeHoras());

        a.setPessoa(perfilPessoa);
        a.setProjeto(projeto);

        this.alocacaoRepository.insert(a);

        return true;
    }

    public List<AlocacaoDto> getAllAlocacao(){
        return this.alocacaoRepository.all()
        .stream()
        .map(a -> new AlocacaoDto(a.getPerfilPessoa().getPessoa().getId(), a.getProjeto().getId(), a.getQuantidadeHoras()))
        .toList();
    }

    public double custoPeriodo(long idProjeto, LocalDate dataInicio, LocalDate dataFim) {
    Projeto projeto = projetoRepository.getById(idProjeto)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

    LocalDate inicio = dataInicio.isBefore(projeto.getDataInicio()) ? projeto.getDataInicio() : dataInicio;
    LocalDate fim = dataFim.isAfter(projeto.getDataFim()) ? projeto.getDataFim() : dataFim;

    long diasPeriodo = ChronoUnit.DAYS.between(inicio, fim) + 1;
    long diasProjeto = ChronoUnit.DAYS.between(projeto.getDataInicio(), projeto.getDataFim()) + 1;

    double total = 0.0;

    List<Alocacao> alocacoes = alocacaoRepository.findByProjetoId(idProjeto);

    for (Alocacao a : alocacoes) {
        List<Contrato> contratos = contratoRepository.getAllByPessoaId(a.getPerfilPessoa().getPessoa().getId());

        for (Contrato c : contratos) {
            boolean valido = !c.getDataFim().isBefore(inicio) && !c.getDataInicio().isAfter(fim);
            if (valido) {
                double horasNoPeriodo = a.getQuantidadeHoras() * ((double) diasPeriodo / diasProjeto);
                total += horasNoPeriodo * c.getSalarioHora();
            }
        }
    }

    return total;
    }

    public double custoTotal(long idProjeto) {
    Projeto projeto = projetoRepository.getById(idProjeto)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

    double total = 0.0;

    List<Alocacao> alocacoes = alocacaoRepository.findByProjetoId(idProjeto);

    for (Alocacao alocacao : alocacoes) {
        Long pessoaId = alocacao.getPerfilPessoa().getPessoa().getId();

        List<Contrato> contratos = contratoRepository.getAllByPessoaId(pessoaId);

        for (Contrato contrato : contratos) {
            boolean cobrePeriodo = !contrato.getDataFim().isBefore(projeto.getDataInicio())
                                && !contrato.getDataInicio().isAfter(projeto.getDataFim());

            if (cobrePeriodo) {
                total += alocacao.getQuantidadeHoras() * contrato.getSalarioHora();
            }
        }
    }

    return total;
    }

}
