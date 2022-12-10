package br.edu.femass.model;

import javax.persistence.Entity;

@Entity
public class Professor extends Leitor{//Ok
    
    private String disciplina;


    public Professor(String nome, String endereço, String telefone, String disciplina){

        super(nome, endereço, telefone);
        this.disciplina = disciplina;
      
    }

    public Professor(){
        this.prazoMaximoDevolucao = 30;
        
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String toString(){
        return this.nome;

    }

}
