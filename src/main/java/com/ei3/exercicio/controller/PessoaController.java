package com.ei3.exercicio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ei3.exercicio.domain.dto.CreatePessoaDto;
import com.ei3.exercicio.domain.dto.PessoaDto;
import com.ei3.exercicio.domain.service.interfaces.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    public PessoaController(){}

    @GetMapping
    public ResponseEntity<List<PessoaDto>> all(){
        var pessoas = this.pessoaService.getAllPessoas();

        return new ResponseEntity<List<PessoaDto>>(pessoas, HttpStatus.OK);
    }

    @GetMapping("/ids")
    public ResponseEntity<List<Long>> listarIds() {
    var ids = pessoaService.getAllIds();
    return new ResponseEntity<>(ids, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<PessoaDto>> one(@PathVariable long id) {
        var pessoa = this.pessoaService.getPessoaById(id);

        return new ResponseEntity<Optional<PessoaDto>>(pessoa, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<String> newPessoa(@RequestBody CreatePessoaDto pessoa){

        var criado = this.pessoaService.createPessoa(pessoa);

        if(criado){
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
