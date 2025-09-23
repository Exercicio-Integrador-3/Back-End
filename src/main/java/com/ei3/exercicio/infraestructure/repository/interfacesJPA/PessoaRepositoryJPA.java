package com.ei3.exercicio.infraestructure.repository.interfacesJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ei3.exercicio.infraestructure.entity.Pessoa;

public interface PessoaRepositoryJPA extends JpaRepository<Pessoa, Long>{

}
