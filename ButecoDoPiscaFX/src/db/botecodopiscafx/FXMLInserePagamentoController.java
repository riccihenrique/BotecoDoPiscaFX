package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALTipoPagto;
import db.entidades.Comanda;
import db.entidades.TipoPagto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class FXMLInserePagamentoController implements Initializable {

    @FXML
    private JFXComboBox<TipoPagto> cbTpPgto;
    @FXML
    private JFXTextField tbValor;
    private Button fechar = new Button();
    private Comanda.Pagamento pag;
    @FXML
    private JFXButton btnInserir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DALTipoPagto dal = new DALTipoPagto();
        List<TipoPagto> tp = dal.get("");
       ObservableList<TipoPagto> ob = FXCollections.observableList(tp);
       cbTpPgto.setItems(ob);
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
        ((Stage) tbValor.getScene().getWindow()).close();
    }

    @FXML
    private void clkInserir(ActionEvent event) {
        //pag = new Comanda.Pagamento();
        pag.setPag_valor(Double.parseDouble(tbValor.getText()));
        pag.setTipo(cbTpPgto.getSelectionModel().getSelectedItem());
        ((Stage) btnInserir.getScene().getWindow()).close();
    }
    
    public Comanda.Pagamento getPgto()
    {
        return pag;
    }
}
