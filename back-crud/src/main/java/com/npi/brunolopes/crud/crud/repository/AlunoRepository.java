package com.npi.brunolopes.crud.crud.repository;

import com.npi.brunolopes.crud.crud.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByAtivo(boolean ativo);
    List<Aluno> findByNome(String nome);
}
