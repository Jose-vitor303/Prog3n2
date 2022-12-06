package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// @Entity
public class Emprestimo {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    // @ManyToOne(cascade = CascadeType.ALL)
    private Leitor leitor;

    // @ManyToOne(cascade = { CascadeType.ALL })
    private Exemplar exemplar;
    
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    

    public Emprestimo(){
       
    }


    public Emprestimo(Exemplar exemplar, Leitor leitor) {
        this.exemplar = exemplar;
        this.leitor = leitor;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = LocalDate.now().plusDays(leitor.getPrazoMaximoDevolucao());
    }
    
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void realizarDevolucao(){
        this.setDataDevolucao(LocalDate.now());
    }


    public String toString(){
        return "Data Emprestimo - " + this.getDataEmprestimo() + "\n"
        +"Data Prevista Devolução - " + this.getDataDevolucao();
        
    }    
}
