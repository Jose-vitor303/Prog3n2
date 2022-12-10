package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ExemplarController implements Initializable{

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonGerar;

    @FXML
    private Button buttonDeletar;

    @FXML 
    private Button buttonInserir;

    @FXML
    private ComboBox<Livro> choiceLivro;

    @FXML
    private TableView<Exemplar> tabelaExemplares = new TableView<Exemplar>();

    @FXML
    private TableColumn<Exemplar, Long> colCodigo = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, LocalDate> colDataAquisicao= new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, Livro> colTitulo = new TableColumn<>();

    private Boolean update;

    private DaoLivro daoLivro = new DaoLivro();
    private DaoExemplar daoExemplar = new DaoExemplar();
    private Exemplar exemplar;
    private Livro livro;


    @FXML
    private void clickExemplar(MouseEvent event){
        exibirDados();
    }

    @FXML
    private void keyExemplar(KeyEvent event){
        exibirDados();
    }


    

    private void exibirDados(){
        exemplar = tabelaExemplares.getSelectionModel().getSelectedItem();

        this.livro = choiceLivro.getSelectionModel().getSelectedItem();

        if (exemplar == null) return;

        choiceLivro.setValue(livro);
    }

    @FXML
    private void incluirExemplar(ActionEvent event) {
        
        editar(true);
        update = false;

        exemplar = new Exemplar();
        // choiceLivro.requestFocus();
    }

    public void preencherTabela(){//Ok
        
        List<Exemplar> exemplares = daoExemplar.buscarTodos();

        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);

        tabelaExemplares.setItems(data);
        tabelaExemplares.refresh();
    
    }

    public void preencherCombo(){

        try {
            List<Livro> livros = daoLivro.buscarTodos();

            ObservableList<Livro> data = FXCollections.observableArrayList(livros);

            for (int i = 0; i < data.size(); i++) {
                choiceLivro.getItems().add(data.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML 
    public void alterarExemplar(){

        editar(true);

        update = true;
    }

        

    @FXML 
    public void deletarExemplar(){

        exemplar = tabelaExemplares.getSelectionModel().getSelectedItem();

        daoExemplar.apagar(exemplar);
        preencherTabela();
        
    }


    public void editar(Boolean habilitar){

        tabelaExemplares.setDisable(habilitar);
     
        choiceLivro.setDisable(!habilitar);
        buttonGerar.setDisable(!habilitar);
        buttonAlterar.setDisable(habilitar);
        buttonInserir.setDisable(habilitar);
        buttonDeletar.setDisable(habilitar);

    }

    @FXML
    public void gravarExemplar(){


        exemplar.setLivro(choiceLivro.getSelectionModel().getSelectedItem());     
        

        if(update){
            daoExemplar.alterar(exemplar);
        }else{
            daoExemplar.inserir(exemplar);
        }

        editar(false);
        preencherTabela();
        
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

               
        colCodigo.setCellValueFactory(new PropertyValueFactory<Exemplar, Long>("codigo"));
        colDataAquisicao.setCellValueFactory(new PropertyValueFactory<Exemplar, LocalDate>("dataAquisicao"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Exemplar, Livro>("livro"));

        buttonGerar.setDisable(true);
        choiceLivro.setDisable(true);

        preencherTabela();
        preencherCombo();        
    }
    
}
