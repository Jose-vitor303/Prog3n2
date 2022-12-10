package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.sound.sampled.Port;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class ProfessorController implements Initializable{

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtEndereco;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtDisciplina;

    @FXML
    private Button buttonAlterar;
  
    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private TableView<Professor> tabelaProfessor = new TableView<Professor>();

    @FXML
    private TableColumn<Professor, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Professor, String> colDisciplina = new TableColumn<>();

    @FXML
    private TableColumn<Professor, String> colEndereco = new TableColumn<>();
    
    @FXML
    private TableColumn<Leitor, Integer> colPrazo = new TableColumn<>();
    
    @FXML
    private TableColumn<Professor, String> colTelefone = new TableColumn<>();


    private DaoProfessor daoProfessor = new DaoProfessor();
    private Professor professor;    
    private Boolean update;



    public void preencherTabela(){//Ok
        
        List<Professor> professores = daoProfessor.buscarTodosPorId();

        ObservableList<Professor> data = FXCollections.observableArrayList(professores);

        tabelaProfessor.setItems(data);
        tabelaProfessor.refresh();

    }

    @FXML
    private void inserirProfessor(){

        editar(true);

        update = false;

        professor = new Professor();
        TxtNome.setText("");
        TxtEndereco.setText("");
        TxtDisciplina.setText("");
        TxtTelefone.setText("");
        TxtNome.requestFocus();

    }

    @FXML
    private void alterarProfessor(){

        editar(true);
        update = true;
    }

    @FXML
    private void deletarProfessor(){
        professor = tabelaProfessor.getSelectionModel().getSelectedItem();

        try {
            daoProfessor.apagar(professor);         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   
        preencherTabela();
        
    }

    @FXML
    private void keyProfessor(){

        exibirDados();
    }

    @FXML
    private void clickProfessor(){

        exibirDados();
    }

    @FXML
    private void atualizarTabela(){

        List<Professor> professores = daoProfessor.buscarTodosPorId();

        ObservableList<Professor> data = FXCollections.observableArrayList(professores);

        tabelaProfessor.setItems(data);
        tabelaProfessor.refresh();

        
    }

    public void editar(Boolean habilitar){

        tabelaProfessor.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtEndereco.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        TxtDisciplina.setDisable(!habilitar);
        buttonCadastrar.setDisable(!habilitar);
        buttonAlterar.setDisable(habilitar);
        buttonInserir.setDisable(habilitar);
        buttonDeletar.setDisable(habilitar);

    }

    @FXML
    private void cadastrarProfessor(ActionEvent event){

        
        professor.setNome(TxtNome.getText());
        professor.setEndereco(TxtEndereco.getText());
        professor.setTelefone(TxtTelefone.getText());
        professor.setDisciplina(TxtDisciplina.getText());


        if(update){
            daoProfessor.alterar(professor);
        }else{
            daoProfessor.inserir(professor);
        }
          
       
        editar(false);
        preencherTabela();
        tabelaProfessor.refresh();


    }

    public void exibirDados(){

        professor = tabelaProfessor.getSelectionModel().getSelectedItem();

        if(professor == null) return;

        TxtNome.setText(professor.getNome());
        TxtEndereco.setText(professor.getEndereco());
        TxtDisciplina.setText(professor.getDisciplina());
        TxtTelefone.setText(professor.getTelefone());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        colNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<Professor, String>("endereco"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<Professor, String>("disciplina"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<Professor, String>("telefone"));
        colPrazo.setCellValueFactory(new PropertyValueFactory<Leitor, Integer>("prazoMaximoDevolucao"));
       

        TxtNome.setDisable(true);
        TxtDisciplina.setDisable(true);
        TxtEndereco.setDisable(true);
        TxtTelefone.setDisable(true);
        buttonCadastrar.setDisable(true);

        preencherTabela();
    }
    
}
