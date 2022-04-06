package com.npi.brunolopes.crud.crud.model;

import javax.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "curso")
    private String curso;
    @Column(name = "ativo")
    private boolean ativo;

    public Aluno() {
    }

    public Aluno(String nome, String curso, boolean ativo) {
        this.nome = nome;
        this.curso = curso;
        this.ativo = ativo;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean isAtivo) {
        this.ativo = isAtivo;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", curso=" + curso + ", ativo=" + ativo + "]";
    }
}
