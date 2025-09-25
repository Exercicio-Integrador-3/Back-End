package com.ei3.exercicio.domain.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public boolean createAlocacao(CreateAlocacaoDto alocacaoDto) {
        // 
        if(alocacaoDto.idsPerfil().size() != alocacaoDto.idsPessoas().size()){
            return false;
        }
        // checar se os perfis existem
        List<Perfil> perfis = alocacaoDto.idsPerfil().stream().map(id -> perfilRepository.getById(id).orElse(null)).toList();
        if (perfis.contains(null)) return false;

        // checar se as pessoas existem 
        List<Pessoa> pessoas = alocacaoDto.idsPessoas().stream().map(id -> pessoaRepository.getById(id).orElse(null)).toList();
        
        if (pessoas.contains(null)) return false;
        // checar se o projeto existe
        Projeto projeto = projetoRepository.getById(alocacaoDto.idProjeto()).orElse(null);
        if (projeto == null) return false;

        // checar se duas pessoas estao tentando exercer o mesmo cargo
        boolean idsPessoasTemDuplicatas = alocacaoDto.idsPessoas().stream()
                                            .collect(Collectors.toSet())
                                            .size() != alocacaoDto.idsPessoas().size(); 
        if(idsPessoasTemDuplicatas){
            return false;
        }
        

        boolean possuiMaisDeUmGerente = alocacaoDto.idsPerfil().stream().filter(id -> id == 1).count() > 1;

        if(possuiMaisDeUmGerente){
            return false;
        }
        

        // pegar perfis pessoas.
        List<PerfilPessoa> pps = pessoas.stream()
                                        .map(p -> this.perfilPessoaRepository.findByPessoaId(p.getId()).orElse(null))
                                        .toList();

        

        // pegar alocacoes desse projeto
        List<Alocacao> alocacoes = this.alocacaoRepository.findByProjetoId(alocacaoDto.idProjeto());  

        // checa se esta vazio
        if(!alocacoes.isEmpty()){
            //ver se alguem da lista para inserção ja possui uma função alocada.
            boolean match = alocacoes.stream().anyMatch((al) -> pps.contains(al.getPerfilPessoa()));
            if(match){
                return false;
            }
        }

        boolean temGerente = alocacoes.stream()
                .anyMatch(a -> a.getPerfilPessoa().getPerfil().getTipo() == TipoPerfil.GERENTE);


        // checa se esta tentando inserir um gerente
        boolean tentaInserirGerente = alocacaoDto.idsPerfil().stream().anyMatch(e -> this.perfilRepository.getById(e).get().tipo == TipoPerfil.GERENTE);

        if (tentaInserirGerente && temGerente) {
            return false;
        }
        //mapa de id pessoas --- perfil para alocar
        Map<Long, TipoPerfil> mapa = new HashMap<>(); 
        //popula um mapa para saber o que cada pessoa irá ser.
        
        for(int i = 0; i < alocacaoDto.idsPessoas().size(); i++){
            mapa.put(alocacaoDto.idsPessoas().get(i), this.perfilRepository.getById(alocacaoDto.idsPerfil().get(i)).get().getTipo());
        }
        // idsPessoas = [1,2,3]
        // idPerfil = [1,2,3]
        // 1 -> 1; 2 -> 2; 3 -> 3
     
        
        for(long key : mapa.keySet()){
            //perfis de cada pessoa
            var perfisDaPessoa = this.perfilPessoaRepository.findAllByPessoaId(key).stream().map(p -> p.getPerfil().tipo).toList();
            PerfilPessoa perfilPessoa;
            Perfil perfil;
            //checa se aquela pessoa tem o perfil ja existente
            if(!perfisDaPessoa.contains(mapa.get(key))){
                //se nao existe, cria.
                perfil = new Perfil();
                perfil.setTipo(mapa.get(key));
                perfil.setId(mapa.get(key).ordinal()+1); // mapa.get(key) retorna o TipoPerfil desejado 
                perfilPessoa = this.perfilPessoaRepository.insert(new PerfilPessoa(this.pessoaRepository.getById(key).get(), perfil));
            }else{
                // se existe busca do repositório
                perfilPessoa = this.perfilPessoaRepository.findByPessoaIdAndTipoPerfil(key, mapa.get(key)).get();
            }
            AlocacaoId alocacaoId = new AlocacaoId(perfilPessoa.getId(), alocacaoDto.idProjeto());
            Alocacao aloc = new Alocacao(alocacaoId, alocacaoDto.quantidadeHoras());
            aloc.setPessoa(perfilPessoa);
            aloc.setProjeto(projeto);
            this.alocacaoRepository.insert(aloc);
        }
        return true;
    }
    
    
    @Override
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

    @Override
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

    @Override
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
