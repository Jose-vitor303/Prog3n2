package br.edu.femass.gui;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AlunoController implements Initializable{

    @FXML
    private TextField TxtNome;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonInserir;
    
    @FXML
    private Button buttonGravar;
    
    
    @FXML
    private TextField TxtEndereco;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtMatricula;

    @FXML
    private TableView<Aluno> tabelaAlunos = new TableView<Aluno>();

    @FXML
    private TableColumn<Aluno, String> colNome = new TableColumn<>();
    
    @FXML
    private TableColumn<Aluno, String> colEndereco = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, String> colMatricula = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, String> colTelefone = new TableColumn<>();
    
    @FXML
    private TableColumn<Leitor, Integer> colPrazo = new TableColumn<>();


    private Boolean update;
    private DaoAluno daoAluno = new DaoAluno();
    private Aluno aluno;

    @FXML
    private void inserirAluno(ActionEvent event){

        editar(true);

        update = false;

        aluno = new Aluno();
        TxtNome.setText("");
        TxtEndereco.setText("");
        TxtMatricula.setText("");
        TxtTelefone.setText("");
        TxtNome.requestFocus();


    }

    @FXML
    private void deletarAluno(ActionEvent event){
        aluno = tabelaAlunos.getSelectionModel().getSelectedItem();

        try {
            daoAluno.apagar(aluno);         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   
        preencherTabela();

    }

    @FXML
    private void alterarAluno(ActionEvent event){

        editar(true);
        update = true;

    }

    @FXML
    private void cadastrarAluno(ActionEvent event){


        aluno.setNome(TxtNome.getText());
        aluno.setEndereco(TxtEndereco.getText());
        aluno.setTelefone(TxtTelefone.getText());
        aluno.setMatricula(TxtMatricula.getText());


        if(update){
            daoAluno.alterar(aluno);
        }else{
            daoAluno.inserir(aluno);
        }
          
       
        editar(false);
        preencherTabela();

    }


    public void preencherTabela(){//Ok
        
        List<Aluno> alunos = daoAluno.buscarTodos();

        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);

        tabelaAlunos.setItems(data);
        tabelaAlunos.refresh();

    }

    @FXML
    private void keyAluno(){
        exibirDados();
    }
    
    @FXML
    private void clickAluno(){
        exibirDados();

    }


    @FXML
    public void atualizarTabela(){

        List<Aluno> autores = daoAluno.buscarTodosPorId();

        ObservableList<Aluno> data = FXCollections.observableArrayList(autores);

        tabelaAlunos.setItems(data);
        tabelaAlunos.refresh();


    }

    
    public void editar(Boolean habilitar){

        tabelaAlunos.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtEndereco.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        TxtMatricula.setDisable(!habilitar);
        buttonGravar.setDisable(!habilitar);
        buttonAlterar.setDisable(habilitar);
        buttonInserir.setDisable(habilitar);
        buttonDeletar.setDisable(habilitar);

    }

    public void exibirDados(){

        aluno = tabelaAlunos.getSelectionModel().getSelectedItem();

        if(aluno == null) return;

        TxtNome.setText(aluno.getNome());
        TxtEndereco.setText(aluno.getEndereco());
        TxtMatricula.setText(aluno.getMatricula());
        TxtTelefone.setText(aluno.getTelefone());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<Aluno, String>("endereco"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));
        colPrazo.setCellValueFactory(new PropertyValueFactory<Leitor, Integer>("prazoMaximoDevolucao"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<Aluno, String>("telefone"));


        TxtNome.setDisable(true);
        TxtMatricula.setDisable(true);
        TxtTelefone.setDisable(true);
        TxtEndereco.setDisable(true);
        buttonGravar.setDisable(true);

        preencherTabela();
    }
    
}
