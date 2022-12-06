package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXMLController implements Initializable {

    @FXML
    private TextField TxtDisciplina;//Professor.fxml

    @FXML
    private void autoresCadastrados(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AutoresCadastrados.fxml"));

            Parent root3 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Autores");
            stage.setScene(new Scene(root3));

            stage.show();
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

    }

    @FXML 
    private void livrosCadastrados(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Livros.fxml"));

            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Livros cadastrados");
            stage.setScene(new Scene(root2));

            stage.show();
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

      
    @FXML
    private void cadastrarLeitor(ActionEvent event){//Scene.fxml
          
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AlertBox.fxml"));

            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Informe a categoria que você se encaixa");
            stage.setScene(new Scene(root2));

            stage.show();
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
       
    }

    @FXML
    public void emprestimos(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Emprestimo.fxml"));

            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Emprestimos");
            stage.setScene(new Scene(root2));

            stage.show();
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
