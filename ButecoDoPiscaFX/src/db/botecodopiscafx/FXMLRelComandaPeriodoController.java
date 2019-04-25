/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.botecodopiscafx;

import com.jfoenix.controls.JFXDatePicker;
import static db.botecodopiscafx.FXMLPrincipalController.gerarRelatorioIntegrado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Alexandre
 */
public class FXMLRelComandaPeriodoController implements Initializable {

    @FXML
    private JFXDatePicker dtPickerIni;
    @FXML
    private JFXDatePicker dtPickerFim;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
        FXMLPrincipalController.efeito(false);
        FXMLPrincipalController.spainelpnprincipal.setCenter(null);
    }

    @FXML
    private void clkConfirmar(ActionEvent event) {
        
        if(dtPickerIni.getValue() != null  && dtPickerFim.getValue() !=null)
        {
            String sql = "SELECT com_data, com_id, com_numero, com_nome, com_desc, gar_nome, com_valor, com_status FROM comanda c INNER JOIN garcon g ON c.gar_id = g.gar_id WHERE c.com_data between '#1' AND '#2' ORDER BY c.com_data";
            sql=sql.replaceAll("#1", dtPickerIni.getValue().toString());
            sql=sql.replaceAll("#2", dtPickerFim.getValue().toString());
            gerarRelatorioIntegrado(sql, "rel/rel_comanda_data.jasper");
        }
    }
    
}
