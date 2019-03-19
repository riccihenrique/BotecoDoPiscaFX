package db.botecodopiscafx;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.entidades.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLInsereProdutoController implements Initializable {

    @FXML
    private JFXComboBox<Produto> cbProduto;
    @FXML
    private JFXTextField tbQuantidade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
    }

    @FXML
    private void clkInserir(ActionEvent event) {
    }
    
}
