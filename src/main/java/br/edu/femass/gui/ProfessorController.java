package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;



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
    private void cadastrarProfessor(ActionEvent event){

        Professor professor = new Professor(TxtNome.getText(), TxtEndereco.getText(), TxtTelefone.getText(), TxtDisciplina.getText());

        System.out.println(professor.getNome());
        System.out.println(professor.getEndereco());
        System.out.println(professor.getTelefone());
        System.out.println(professor.getDisciplina());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    
}
