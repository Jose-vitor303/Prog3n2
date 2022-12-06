package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.edu.femass.model.Emprestimo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmprestimoController implements Initializable{

    @FXML
    private TableView<Emprestimo> tabelaEmprestimos = new TableView<Emprestimo>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataEmprestimo = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataPrevista = new TableColumn<>();

 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    


}
