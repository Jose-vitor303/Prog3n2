package br.edu.femass.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import net.bytebuddy.asm.Advice.Local;


@Entity
public class Emprestimo { //Ok 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Leitor leitor;

    @ManyToOne(cascade = { CascadeType.ALL })
    private Exemplar exemplar;
    
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    private Boolean atrasado;
    

    public Emprestimo(){

        this.dataEmprestimo = LocalDate.now();      
       
    }


    public Emprestimo(Exemplar exemplar, Leitor leitor) {
        this.exemplar = exemplar;
        this.leitor = leitor;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(leitor.getPrazoMaximoDevolucao());
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

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Long getId() {
        return id;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean emprestimosAtrasado() {
        return this.getDataPrevistaDevolucao().isBefore(LocalDate.now());
        // return this.getDataPrevistaDevolucao().isBefore(LocalDate.of(2023, 1, 2));
    }
    
    public void setAtrasado(Boolean atrasado) {
        this.atrasado = atrasado;
    }

    public Boolean getAtrasado() {
        return atrasado;
    }


    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void realizarDevolucao(){
        this.setDataDevolucao(LocalDate.now());
    }
    
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public String toString(){
        return "Data Emprestimo - " + this.getDataEmprestimo() + "\n"
        +"Data Prevista Devolução - " + this.getDataDevolucao();
        
    }    
}
