package com.ei3.exercicio.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ei3.exercicio.domain.dto.AlocacaoDto;
import com.ei3.exercicio.domain.dto.CreateAlocacaoDto;
import com.ei3.exercicio.domain.service.interfaces.AlocacaoService;

@RestController
@RequestMapping("/alocacao")
public class AlocacaoController {
    
    @Autowired
    private AlocacaoService alocacaoService;

    public AlocacaoController(){}

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateAlocacaoDto alocacaoDto){
        var criado = this.alocacaoService.createAlocacao(alocacaoDto);

        if(criado == true){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<AlocacaoDto>> listAll(){
        var alocacoes = this.alocacaoService.getAllAlocacao();

        return new ResponseEntity<List<AlocacaoDto>>(alocacoes, HttpStatus.OK);
    }

    @GetMapping("/custo/{id}")
    public ResponseEntity<String> getCusto(@PathVariable long id){
        var custo = this.alocacaoService.custoTotal(id);

        return ResponseEntity.ok(String.format("%.3f", custo));
    }

    @GetMapping("/custo/{id}/{dataInicio}/{dataFim}")
    public ResponseEntity<String> getCustoIntervalo(@PathVariable long id, @PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFim){
        var custo = this.alocacaoService.custoPeriodo(id, dataInicio, dataFim);

        return ResponseEntity.ok(String.format("%.3f", custo));
    }
}
