package br.edu.femass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)//Cria uma tabela para cada classe subclasse e superclasse
public class Leitor {//Ok

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String nome;
    protected String endereco;
    protected String telefone;
    protected Integer prazoMaximoDevolucao;


    public Leitor(String nome, String endereco, String telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Leitor(){

    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public Integer getPrazoMaximoDevolucao() {
        return prazoMaximoDevolucao;
    }

    public void setPrazoMaximoDevolucao(Integer prazoMaximoDevolucao) {
        this.prazoMaximoDevolucao = prazoMaximoDevolucao;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public String toString(){

        return this.nome;
    }

    



}
