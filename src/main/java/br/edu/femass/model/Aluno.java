package br.edu.femass.model;

import javax.persistence.Entity;
import javax.print.DocFlavor.STRING;

@Entity
public class Aluno extends Leitor{//Ok
    
    private String matricula;


    public Aluno(String nome, String endereco, String telefone, String matricula){

        super(nome, endereco, telefone);
        this.matricula = matricula;
      
    }

    public Aluno(){
        this.prazoMaximoDevolucao = 15;
        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String toString(){
        return this.nome;

    }



    
}
