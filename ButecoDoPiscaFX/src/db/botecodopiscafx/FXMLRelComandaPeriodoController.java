
package db.botecodopiscafx;

import com.jfoenix.controls.JFXDatePicker;
import static db.botecodopiscafx.FXMLPrincipalController.gerarRelatorioIntegrado;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLRelComandaPeriodoController implements Initializable {

    @FXML
    private JFXDatePicker dtPickerIni;
    @FXML
    private JFXDatePicker dtPickerFim;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dtPickerIni.setValue(LocalDate.now());
        dtPickerFim.setValue(LocalDate.now());
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
            gerarRelatorioIntegrado(sql, "rel/rel_comanda_data.jasper", dtPickerIni.getValue().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")) + " à " + dtPickerFim.getValue().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")), "periodo");
        }
    }
}
