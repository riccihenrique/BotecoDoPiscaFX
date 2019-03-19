/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.botecodopiscafx;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.entidades.TipoPagto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
public class FXMLInserePagamentoController implements Initializable {

    @FXML
    private JFXComboBox<TipoPagto> cbTpPgto;
    @FXML
    private JFXTextField tbValor;
    
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
