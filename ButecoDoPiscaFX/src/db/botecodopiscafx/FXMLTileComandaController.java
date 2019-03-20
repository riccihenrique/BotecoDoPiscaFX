
package db.botecodopiscafx;

import db.entidades.Comanda;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FXMLTileComandaController implements Initializable {

    private Comanda comanda;
    @FXML
    private Label lbComanda;
    @FXML
    private AnchorPane painel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    
    public void setComanda(Comanda c)
    {
        comanda = c;
        lbComanda.setText(lbComanda.getText().replace("#", ""+comanda.getCom_numero()));
    }

    @FXML
    private void BtnAbrir(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerenciamentoComanda.fxml"));
        Parent root = (Parent) loader.load();
        FXMLGerenciamentoComandaController ger = loader.getController();
        ger.setComanda(comanda);
        FXMLPrincipalController.spainelpnprincipal.setCenter(root);
    }
    
    public void setCor(String cor)
    {
        painel.setStyle("-fx-background-color: " + cor + ";");
    }
}
