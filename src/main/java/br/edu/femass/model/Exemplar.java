package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @Entity
public class Exemplar {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    private Long codigo;
    private LocalDate dataAquisicao;
    private Livro livro;

    public Exemplar(){

    }

    public Exemplar(Livro livro){
        this.dataAquisicao = LocalDate.now();
        this.livro = livro;
    }

    public Long getCodigo() {
        return codigo;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }
}
