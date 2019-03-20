package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALComanda;
import db.dal.DALGarcon;
import db.entidades.Comanda;
import db.entidades.Garcon;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLComandaAbrirController implements Initializable {

    @FXML
    private JFXButton BtnCancelar;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private JFXTextField tbNome;
    @FXML
    private JFXTextField tbDescricao;
    @FXML
    private JFXComboBox<Garcon> cbGarcon;
    @FXML
    private JFXDatePicker dtData;
    @FXML
    private JFXTextField tbMesa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dtData.setValue(LocalDate.now());
        
        DALGarcon dal = new DALGarcon();
        
        List <Garcon> dados = dal.get("");
        ObservableList <Garcon> obsList= FXCollections.observableList(dados);
        cbGarcon.setItems(obsList);
    }    

    @FXML
    private void clkBtnCancelar(ActionEvent event)
    {
        FXMLPrincipalController.efeito(false);
        FXMLPrincipalController.spainelpnprincipal.setCenter(null);
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event) 
    {
        Comanda c = new Comanda(cbGarcon.getValue(), Integer.parseInt(tbMesa.getText()), tbNome.getText(), dtData.getValue(), tbDescricao.getText(), 0, 'A');
        
        DALComanda dal = new DALComanda();
        
        if(dal.gravar(c))
        {
            FXMLPrincipalController.efeito(false);
            FXMLPrincipalController.spainelpnprincipal.setCenter(null);
        }
        else
            System.out.println("Erro");
    }
}
