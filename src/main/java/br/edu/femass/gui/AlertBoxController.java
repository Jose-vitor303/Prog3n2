package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AlertBoxController implements Initializable{

    @FXML
    private void alunoSelecionado(ActionEvent event){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Aluno.fxml"));

            Parent root3 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Cadastrando aluno");
            stage.setScene(new Scene(root3));

            stage.show();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void professorSelecionado(ActionEvent event){//AlertBox.fxml
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Professor.fxml"));

            Parent root4 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Cadastrando Professor");
            stage.setScene(new Scene(root4));

            stage.show();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }


    
}
