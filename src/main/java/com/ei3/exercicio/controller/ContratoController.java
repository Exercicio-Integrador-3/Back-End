package com.ei3.exercicio.controller;

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

import com.ei3.exercicio.domain.dto.ContratoDto;
import com.ei3.exercicio.domain.dto.CreateContratoDto;
import com.ei3.exercicio.domain.service.interfaces.ContratoService;

@RestController
@RequestMapping("/contrato")
public class ContratoController {
    
    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public ResponseEntity<List<ContratoDto>> all(){
        var contratos = this.contratoService.getAllContratos();
        return new ResponseEntity<>(contratos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ContratoDto>> byPessoaId(@PathVariable long id) {
        var contrato = this.contratoService.getContratosByPessoaId(id);
        if(contrato.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contrato, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> newContrato(@RequestBody CreateContratoDto contratoDto){
        var criado = this.contratoService.createContrato(contratoDto);
        
        if(criado){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

    }

}
