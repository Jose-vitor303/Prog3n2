package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LivroController implements Initializable{

    @FXML
    private Button buttonGravar;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonInserir;

    
    @FXML
    private ComboBox<Autor> choiceAutor;

    @FXML
    private TableView<Livro> tabelaLivros = new TableView<Livro>();

    @FXML
    private TableColumn<Livro, Long> colid = new TableColumn<>();

    @FXML
    private TableColumn<Livro, String> colTitulo = new TableColumn<>();

    @FXML
    private TableColumn<Livro, Autor> colAutor = new TableColumn<>();
    
    @FXML
    private TextField txtTitulo;


    private Livro livro;
    private Boolean update;
    private DaoLivro dao = new DaoLivro();
    private DaoAutor daoAutor = new DaoAutor();


    @FXML
    public void atualizarTabela(){

        preencherTabela();
    }

    public void preencherCombo(){

        try {
            List<Autor> autor = daoAutor.buscarTodos();

            ObservableList<Autor> data = FXCollections.observableArrayList(autor);

            for (int i = 0; i < data.size(); i++) {
                choiceAutor.getItems().add(data.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void preencherTabela(){//Ok
        
        List<Livro> livros = dao.buscarTodosPorId();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);

        tabelaLivros.setItems(data);
        tabelaLivros.refresh();


    }


    @FXML
    public void inserirLivro(){

        editar(true);
        update = false;

        livro = new Livro();
        txtTitulo.setText("");
      
    }

    @FXML
    public void alterarLivro(){
        editar(true);
        update = true;
    }

    @FXML 
    public void clickLivro(){
        exibirDados();
    }

    @FXML
    public void keyLivro(){
        exibirDados();
    }

    @FXML
    public void deletarLivro(){

        livro = tabelaLivros.getSelectionModel().getSelectedItem();

        
        try {
            dao.apagar(livro);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Há um exemplar relaciona a esse livro, Favor remover o exemplar primeiro");
        }


        preencherTabela();
    }


    @FXML
    public void gravarLivro(){

        Autor autor = choiceAutor.getSelectionModel().getSelectedItem();
        livro.setTitulo(txtTitulo.getText());
        
        livro.setAutor(autor);
        

        if(update){
            dao.alterar(livro);
        }else{
            dao.inserir(livro);
        }

        editar(false);
        preencherTabela();
    }

    public void exibirDados(){

        livro = tabelaLivros.getSelectionModel().getSelectedItem();
        Autor autor = choiceAutor.getSelectionModel().getSelectedItem();

        if(livro == null) return;

        txtTitulo.setText(livro.getTitulo());
        choiceAutor.setValue(autor);
      
    }


    public void editar(Boolean habilitar){

        tabelaLivros.setDisable(habilitar);
        txtTitulo.setDisable(!habilitar);
        buttonGravar.setDisable(!habilitar);
        choiceAutor.setDisable(!habilitar);
        buttonAlterar.setDisable(habilitar);
        buttonInserir.setDisable(habilitar);
        buttonDeletar.setDisable(habilitar);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

           
        colid.setCellValueFactory(new PropertyValueFactory<Livro, Long>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Livro, Autor>("autor"));

        txtTitulo.setDisable(true);
        choiceAutor.setDisable(true);
        buttonGravar.setDisable(true);

        preencherTabela();
        preencherCombo();
        
    }
    
}
