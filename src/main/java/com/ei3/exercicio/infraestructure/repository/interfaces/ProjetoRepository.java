package com.ei3.exercicio.infraestructure.repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.ei3.exercicio.infraestructure.entity.Projeto;

public interface ProjetoRepository{
   public Projeto insert(Projeto projeto);
   public List<Projeto> getAll();
   public Optional<Projeto> getById(long id);
   void updateStatus(Projeto projeto);
}
