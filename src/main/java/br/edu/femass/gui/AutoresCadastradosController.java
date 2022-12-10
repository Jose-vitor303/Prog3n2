package br.edu.femass.gui;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AutoresCadastradosController implements Initializable{

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNacionalidade;

    @FXML
    private TextField txtSobrenome;

    @FXML
    private Button buttonGravar;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonAlterar;

    @FXML
    private TableView<Autor> tabela = new TableView<Autor>();

    @FXML
    private TableColumn<Autor, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> colSobrenome = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> colNacionalidade = new TableColumn<>();

    private DaoAutor dao = new DaoAutor();

    private Autor autor;

    private Boolean update;
    // private Boolean habilitar;

    @FXML
    public void atualizarTabela(){//Ok
        
        List<Autor> autores = dao.buscarTodosPorId();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);

        tabela.setItems(data);
    }

    public void preencherTabela(){//Ok
        
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);

        tabela.setItems(data);

    }

    @FXML
    private void excluirautor(ActionEvent event){

        autor = tabela.getSelectionModel().getSelectedItem();

        try {
            dao.apagar(autor);         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Primeiro remova o livro relacionado ao autor");
        }
   
        preencherTabela();

    }

    @FXML
    public void btnAlterar(ActionEvent event){

        editar(true);
        update = true;

    }

    @FXML
    public void clickTabela(MouseEvent event){
        exibirDados();
    }

    @FXML
    public void keyTabela(KeyEvent event){
        exibirDados();
    }

    @FXML
    public void btnInserir(){

        editar(true);

        update = false;

        autor = new Autor();
        txtNome.setText("");
        txtNacionalidade.setText("");
        txtSobrenome.setText("");
        txtNome.requestFocus();
        
    }

    @FXML 
    public void btnGravar(){
        autor.setNome(txtNome.getText());
        autor.setSobreNome(txtSobrenome.getText());
        autor.setNacionalidade(txtNacionalidade.getText());


        if(update){
            dao.alterar(autor);
        }else{
            dao.inserir(autor);
        }
          
       
        editar(false);
        preencherTabela();
    }

    public void exibirDados(){
        autor = tabela.getSelectionModel().getSelectedItem();

        if(autor == null) return;

        txtNome.setText(autor.getNome());
        txtSobrenome.setText(autor.getSobreNome());
        txtNacionalidade.setText(autor.getNacionalidade());

    }

    public void clear(){

        txtNome.setText(null);
        txtSobrenome.setText(null);
        txtNacionalidade.setText(null);
    }

    private void editar(boolean habilitar) {

        tabela.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtSobrenome.setDisable(!habilitar);
        txtNacionalidade.setDisable(!habilitar);
        buttonGravar.setDisable(!habilitar);
        buttonAlterar.setDisable(habilitar);
        buttonInserir.setDisable(habilitar);
        buttonDeletar.setDisable(habilitar);


    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        colNome.setCellValueFactory(new PropertyValueFactory<Autor, String>("nome"));
        colSobrenome.setCellValueFactory(new PropertyValueFactory<Autor, String>("sobreNome"));
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<Autor, String>("nacionalidade"));

        txtNome.setDisable(true);
        txtSobrenome.setDisable(true);
        txtNacionalidade.setDisable(true);
     
        preencherTabela();
     
    }
    
}
