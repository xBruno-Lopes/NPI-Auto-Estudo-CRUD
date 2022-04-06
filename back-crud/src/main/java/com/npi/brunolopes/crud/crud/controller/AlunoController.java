package com.npi.brunolopes.crud.crud.controller;

import com.npi.brunolopes.crud.crud.model.Aluno;
import com.npi.brunolopes.crud.crud.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AlunoController {
    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> getAllAlunos(@RequestParam(required = false) String nome) {
        try {
            List<Aluno> alunos = new ArrayList<Aluno>();
            if (nome == null)
                alunoRepository.findAll().forEach(alunos::add);
            else
                alunoRepository.findByNome(nome).forEach(alunos::add);
            if (alunos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") long id) {
        Optional<Aluno> tutorialData = alunoRepository.findById(id);
        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/alunos")
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        try {
            Aluno _aluno = alunoRepository
                    .save(new Aluno(aluno.getNome(), aluno.getCurso(), true));
            return new ResponseEntity<>(_aluno, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/alunos/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable("id") long id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoData = alunoRepository.findById(id);
        if (alunoData.isPresent()) {
            Aluno _aluno = alunoData.get();
            _aluno.setNome(aluno.getNome());
            _aluno.setCurso(aluno.getCurso());
            _aluno.setAtivo(aluno.isAtivo());
            return new ResponseEntity<>(alunoRepository.save(_aluno), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/alunos/{id}")
    public ResponseEntity<HttpStatus> deleteAluno(@PathVariable("id") long id) {
        try {
            alunoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/alunos")
    public ResponseEntity<HttpStatus> deleteAllAlunos() {
        try {
            alunoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/alunos/ativo")
    public ResponseEntity<List<Aluno>> findByAtivo() {
        try {
            List<Aluno> alunos = alunoRepository.findByAtivo(true);
            if (alunos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}