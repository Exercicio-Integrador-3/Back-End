package com.ei3.exercicio.domain.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ei3.exercicio.domain.dto.AlocacaoDto;
import com.ei3.exercicio.domain.dto.CreateAlocacaoDto;
import com.ei3.exercicio.domain.service.interfaces.AlocacaoService;
import com.ei3.exercicio.infraestructure.entity.Alocacao;
import com.ei3.exercicio.infraestructure.entity.AlocacaoId;
import com.ei3.exercicio.infraestructure.entity.Contrato;
import com.ei3.exercicio.infraestructure.entity.Perfil;
import com.ei3.exercicio.infraestructure.entity.PerfilPessoa;
import com.ei3.exercicio.infraestructure.entity.Pessoa;
import com.ei3.exercicio.infraestructure.entity.Projeto;
import com.ei3.exercicio.infraestructure.entity.TipoPerfil;
import com.ei3.exercicio.infraestructure.repository.interfaces.AlocacaoRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.ContratoRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.PerfilPessoaRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.PerfilRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.PessoaRepository;
import com.ei3.exercicio.infraestructure.repository.interfaces.ProjetoRepository;

@Service
public class AlocacaoImplService implements AlocacaoService{
    
    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PerfilPessoaRepository perfilPessoaRepository;

    public AlocacaoImplService(){}

    public boolean createAlocacao(CreateAlocacaoDto alocacaoDto) {
        Perfil perfil = perfilRepository.getById(alocacaoDto.idPerfil()).orElse(null);
        if (perfil == null) return false;

        Pessoa pessoa = pessoaRepository.getById(alocacaoDto.idPessoa()).orElse(null);
        if (pessoa == null) return false;
        
        Projeto projeto = projetoRepository.getById(alocacaoDto.idProjeto()).orElse(null);
        if (projeto == null) return false;

        PerfilPessoa pp = perfilPessoaRepository
                .findByPessoaId(pessoa.getId())
                .stream()
                .filter(ppTemp -> ppTemp.getPerfil().getId() == perfil.getId())
                .findFirst()
                .orElseGet(() -> this.perfilPessoaRepository.insert(new PerfilPessoa(pessoa, perfil)));

        List<Alocacao> alocacoesProjeto = alocacaoRepository.findByProjetoId(alocacaoDto.idProjeto());

        boolean pessoaJaAlocada = alocacoesProjeto.stream()
                .anyMatch(a -> a.getPerfilPessoa().getPessoa().getId() == pessoa.getId());
        if (pessoaJaAlocada) return false;

        long qtdGerentes = alocacoesProjeto.stream()
                .filter(a -> a.getPerfilPessoa().getPerfil().getTipo() == TipoPerfil.GERENTE)
                .count();

        if (perfil.getTipo() == TipoPerfil.GERENTE && qtdGerentes >= 1) {
            return false;
        }

        AlocacaoId alocacaoId = new AlocacaoId(pp.getId(), alocacaoDto.idProjeto());
        Alocacao a = new Alocacao(alocacaoId, alocacaoDto.quantidadeHoras());
        a.setPessoa(pp); 
        a.setProjeto(this.projetoRepository.getById(alocacaoId.getIdProjeto()).get());
        this.alocacaoRepository.insert(a);

        return true;
    }

    public List<AlocacaoDto> getAllAlocacao(){
        return this.alocacaoRepository.all()
        .stream()
        .map(AlocacaoDto::fromModel)
        .toList();
    }

    private long contarDiasUteis(LocalDate inicio, LocalDate fim) {
        long diasUteis = 0;
        LocalDate data = inicio;

        while (!data.isAfter(fim)) {
            DayOfWeek dia = data.getDayOfWeek();
            if (dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) {
                diasUteis++;
            }
            data = data.plusDays(1);
        }

        return diasUteis; 
    }

    public double custoPeriodo(long idProjeto, LocalDate dataInicio, LocalDate dataFim) {
        Projeto projeto = projetoRepository.getById(idProjeto)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        LocalDate inicio = dataInicio.isBefore(projeto.getDataInicio()) ? projeto.getDataInicio() : dataInicio;
        LocalDate fim = dataFim.isAfter(projeto.getDataFim()) ? projeto.getDataFim() : dataFim;

        long diasPeriodo = contarDiasUteis(inicio, fim);

        double total = 0.0;

        List<Alocacao> alocacoes = alocacaoRepository.findByProjetoId(idProjeto);

        for (Alocacao a : alocacoes) {
            List<Contrato> contratos = contratoRepository.getAllByPessoaId(a.getPerfilPessoa().getPessoa().getId());

            for (Contrato c : contratos) {
                boolean valido = !c.getDataFim().isBefore(inicio) && !c.getDataInicio().isAfter(fim);
                if (valido) {
                    //horas por dia = 35 horas por semana / 5
                    int hDia = c.getHorasSemanais() / 5;
                    int totalHoras = hDia * (int)diasPeriodo;
                    if(totalHoras>=a.getQuantidadeHoras()){
                        total += a.getQuantidadeHoras() * c.getSalarioHora();
                    }  else{
                        total += totalHoras * c.getSalarioHora();
                    }
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
                LocalDate inicio = contrato.getDataInicio().isBefore(projeto.getDataInicio())
                        ? projeto.getDataInicio()
                        : contrato.getDataInicio();

                LocalDate fim = contrato.getDataFim().isAfter(projeto.getDataFim())
                        ? projeto.getDataFim()
                        : contrato.getDataFim();

                if (!inicio.isAfter(fim)) {
                    long semanasUteis = contarDiasUteis(inicio, fim);
                    long maxHorasContrato = semanasUteis * contrato.getHorasSemanais();

                    long horasTrabalhadas = Math.min(alocacao.getQuantidadeHoras(), maxHorasContrato);

                    total += horasTrabalhadas * contrato.getSalarioHora();
                }
            }
        }

        return total;
    }
}
