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


import com.ei3.exercicio.domain.dto.CreateProjetoDto;
import com.ei3.exercicio.domain.dto.ProjetoDto;
import com.ei3.exercicio.domain.service.interfaces.ProjetoService;


@RestController
@RequestMapping("/projeto")
public class ProjetoController {
    
    @Autowired
    private ProjetoService projetoService;

    public ProjetoController(){}

    @GetMapping
    public ResponseEntity<List<ProjetoDto>> all(){
        var projetos = this.projetoService.getAllProjetos();

        return new ResponseEntity<List<ProjetoDto>>(projetos, HttpStatus.OK);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProjetoDto>> one(@PathVariable long id) {
        var projeto = this.projetoService.getProjetoById(id);

        return new ResponseEntity<Optional<ProjetoDto>>(projeto, HttpStatus.OK);
    }
    
    @GetMapping("/projeto-ids")
    public ResponseEntity<List<Long>> listarIds() {
    var ids = projetoService.getAllIds();
    return new ResponseEntity<>(ids, HttpStatus.OK);
}

    
    @PostMapping()
    public ResponseEntity<String> newProject(@RequestBody CreateProjetoDto projeto){

        var criado = this.projetoService.createProject(projeto);

        if(criado){
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }

        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}
