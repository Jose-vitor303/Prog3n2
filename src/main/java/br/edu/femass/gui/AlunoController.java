package br.edu.femass.gui;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
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

public class AlunoController implements Initializable{

    @FXML
    private TextField TxtNome;//Aluno.fxml

    @FXML
    private TextField TxtEndereco;//Aluno.fxml

    @FXML
    private TextField TxtTelefone;//Aluno.fxml

    @FXML
    private TextField TxtMatricula;

    @FXML
    private Label codigoLeitor;


    @FXML
    private void cadastrarAluno(ActionEvent event){//Aluno.fxml

        Aluno aluno = new Aluno(TxtNome.getText(), TxtEndereco.getText(), TxtTelefone.getText(), TxtMatricula.getText());
        
        DaoAluno dao = new DaoAluno();

        dao.inserir(aluno);

          
        // try {
        //     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AlertBox.fxml"));

        //     Parent root1 = (Parent) fxmlLoader.load();
        //     Stage stage = new Stage();

        //     stage.setTitle("Alert Box");
        //     stage.setScene(new Scene(root1));

        //     stage.show();
            
            
        // } catch (Exception e) {
        //    System.out.println(e.getMessage());
        // }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }
    
}
