package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exemplar {//Ok

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private LocalDate dataAquisicao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro;


    public Exemplar(Livro livro){
        this.livro = livro;
        this.dataAquisicao = LocalDate.now();
      
    }

    public Exemplar(){
        this.dataAquisicao = LocalDate.now();
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String toString(){
        return (""+this.getLivro());
    }


    
}
