package br.edu.femass.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobreNome;
    private String nacionalidade;

    @OneToOne(cascade = { CascadeType.ALL })
    private Livro livro;

   
    public Autor(){

    }

    public Autor(String nome, String sobreNome, String nacionalidade){

        this.nome = nome;
        this.sobreNome = sobreNome;
        this.nacionalidade = nacionalidade;
        
    }

  
    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getNome() {
        return nome;
    }
    
    public String getSobreNome() {
        return sobreNome;
    }


    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String toString(){
        return this.nome;

        
    }

    
}
