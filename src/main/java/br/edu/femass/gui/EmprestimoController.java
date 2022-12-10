package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmprestimoController implements Initializable{

    @FXML
    private Button buttonGravar;

    @FXML
    private Button buttonDevolver;

    @FXML 
    private Button buttonInserir;

    @FXML
    private Button buttonDeletar;



    @FXML
    private ComboBox<Leitor> choiceLeitor;

    @FXML
    private ComboBox<Exemplar> choiceExemplar;

    @FXML
    private TableView<Emprestimo> tabelaEmprestimos = new TableView<Emprestimo>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataEmprestimo = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataPrevista = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> colDevolucao = new TableColumn<>();

    @FXML
    private TableColumn<Leitor, String> colLeitor = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, Boolean> colAtrasado = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, String> colExemplar = new TableColumn<>();

    private Leitor leitor;
    private Exemplar exemplar;
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();
    private DaoExemplar daoExemplar = new DaoExemplar();
    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();

    private Boolean update;
    private Emprestimo emprestimo;


    public void editar(Boolean habilitar){

        tabelaEmprestimos.setDisable(habilitar);
        choiceExemplar.setDisable(!habilitar);
        choiceLeitor.setDisable(!habilitar);
        buttonGravar.setDisable(!habilitar);
        buttonDevolver.setDisable(habilitar);
        buttonInserir.setDisable(habilitar);
        buttonDeletar.setDisable(habilitar);



    }

    public void preencherComboExemplar(){

        try {
            List<Exemplar> exemplares = daoExemplar.buscarTodos();

            ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);

            for (int i = 0; i < data.size(); i++) {
                choiceExemplar.getItems().add(data.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void inserirEmprestimo(ActionEvent event) {
        
        editar(true);
        update = false;

        emprestimo = new Emprestimo();
    }



    
    public void preencherComboProfessor(){

        try {
            List<Professor> professores = daoProfessor.buscarTodos();

            ObservableList<Professor> data = FXCollections.observableArrayList(professores);

            for (int i = 0; i < data.size(); i++) {
                choiceLeitor.getItems().add(data.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public void preencherComboAluno(){

        try {
            List<Aluno> alunos = daoAluno.buscarTodos();

            ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);

            for (int i = 0; i < data.size(); i++) {
                choiceLeitor.getItems().add(data.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void deletarEmprestimo(){

        emprestimo = tabelaEmprestimos.getSelectionModel().getSelectedItem();

        daoEmprestimo.apagar(emprestimo);

        preencherTabela();
    }

    @FXML
    public void devolverEmprestimo(){

        editar(false);

        emprestimo = tabelaEmprestimos.getSelectionModel().getSelectedItem();

        emprestimo.setDataDevolucao(LocalDate.now());
        // emprestimo.setDataDevolucao(LocalDate.of(2023, 2, 1));

        daoEmprestimo.alterar(emprestimo);
        preencherTabela();

    }




 
    @FXML 
    public void gravarEmprestimo(){

        exemplar = choiceExemplar.getSelectionModel().getSelectedItem();
        leitor = choiceLeitor.getSelectionModel().getSelectedItem();

        emprestimo = new Emprestimo(exemplar, leitor);
        
        emprestimo.setExemplar(exemplar);
        emprestimo.setLeitor(leitor);

        daoEmprestimo.inserir(emprestimo);

        editar(false);
        preencherTabela();

    }

    public void preencherTabela(){//Ok
        
        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();

        ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimos);

        tabelaEmprestimos.setItems(data);
        tabelaEmprestimos.refresh();
    
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();

        System.out.println(emprestimos);

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.emprestimosAtrasado()) {
                emprestimo.setAtrasado(true);
            } else {
                emprestimo.setAtrasado(false);
            }
        }
        
        colDataEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo"));
        colDataPrevista.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao"));
        colDevolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));
        colExemplar.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("exemplar"));
        colLeitor.setCellValueFactory(new PropertyValueFactory<Leitor, String>("leitor"));
        colAtrasado.setCellValueFactory(new PropertyValueFactory<Emprestimo, Boolean>("atrasado"));
      
        

        preencherComboAluno();
        preencherComboProfessor();
        preencherComboExemplar();
        preencherTabela();

        buttonGravar.setDisable(true);
        choiceExemplar.setDisable(true);
        choiceLeitor.setDisable(true);

    
    }

    


}
