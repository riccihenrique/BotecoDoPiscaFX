package db.botecodopiscafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class FXMLPrincipalController implements Initializable 
{    
    private Label label;
    @FXML
    private BorderPane painelpnprincipal;
    
    public static BorderPane spainelpnprincipal = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        spainelpnprincipal = painelpnprincipal;
        efeito(false);
    }
    
    @FXML
    private void clkCadProduto(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADProduto.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
         
    }  

    @FXML
    private void clkCadGarcon(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADGarcon.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadUnidade(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADUnidade.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadCategoria(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADCategoria.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadTpPagamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADTpPagamento.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkAbrir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLComandaAbrir.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkGerenciar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPainelComanda.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }
    
    public static void efeito(boolean on)
    {
        if(on)
        {
//            FadeTransition ft = new FadeTransition(Duration.millis(500), spnprincipal);
//            ft.setFromValue(1.0);
//            ft.setToValue(0.5);
//            ft.play(); 
              spainelpnprincipal.setStyle("-fx-background-image: url('icons/textura2.png');");

        }
        else
            spainelpnprincipal.setStyle("-fx-background-image: url('icons/textura.png');");
            
    }
}
