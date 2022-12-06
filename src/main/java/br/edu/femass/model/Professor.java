package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Professor extends Leitor{
    
    private String disciplina;


    public Professor(String nome, String endereço, String telefone, String disciplina){

        super(nome, endereço, telefone);
        this.disciplina = disciplina;
        this.prazoMaximoDevolucao = 30;
    }

    public Professor(){

    }

    public String getDisciplina() {
        return disciplina;
    }
}
