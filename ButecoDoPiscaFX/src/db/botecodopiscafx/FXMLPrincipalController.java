package db.botecodopiscafx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLPrincipalController 
{    
    private Label label;
    @FXML
    private BorderPane painelpnprincipal;
    //
    @FXML
    private void clkCadProduto(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADProduto.fxml"));

        //painelpnprincipal.setCenter(root);
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
         
    }  

    @FXML
    private void clkCadGarcon(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADGarcon.fxml"));
        
        //painelpnprincipal.setCenter(root);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
          
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkCadUnidade(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADUnidade.fxml"));
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadCategoria(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADCategoria.fxml"));
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadTpPagamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADTpPagamento.fxml"));
        painelpnprincipal.setCenter(root);
    }
}
