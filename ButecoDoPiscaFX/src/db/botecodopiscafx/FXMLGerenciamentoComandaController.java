
package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.entidades.Comanda;
import db.entidades.Garcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLGerenciamentoComandaController implements Initializable {

    @FXML
    private JFXButton BtnAlterar;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton BtnCancelar;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private JFXTextField tbNome;
    @FXML
    private JFXTextField tbDescricao;
    @FXML
    private TableColumn<Comanda.Item, String> colCod;
    @FXML
    private TableColumn<Comanda.Item, String> colNome;
    @FXML
    private JFXButton BtnFechar;
    @FXML
    private JFXTextField tbValor;
    @FXML
    private JFXComboBox<Garcon> cbGarcon;
    @FXML
    private JFXTextField tbMesa;
    @FXML
    private JFXDatePicker dtData;
    @FXML
    private TableColumn<Comanda.Pagamento, String> colCod1;
    @FXML
    private TableView<Comanda.Item> tbvItens;
    @FXML
    private TableColumn<Comanda.Item, String> colQtde;
    @FXML
    private TableColumn<Comanda.Item, String> colPreco;
    @FXML
    private TableView<Comanda.Pagamento> tbvPagamentos;
    @FXML
    private TableColumn<Comanda.Pagamento, String> colTipo;
    @FXML
    private TableColumn<Comanda.Pagamento, String> colValor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 

    @FXML
    private void clkBtnAlterar(ActionEvent event) {
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) {
    }


    @FXML
    private void clkTabela(MouseEvent event) {
    }

    @FXML
    private void clkBtnFechar(ActionEvent event) {
    }

    @FXML
    private void clkBtnInserirItem(ActionEvent event) {
    }

    @FXML
    private void clkBtnRemoverItem(ActionEvent event) {
    }

    @FXML
    private void clkBtnInserirPagamento(ActionEvent event) {
    }

    @FXML
    private void clkBtnRemoverPagamento(ActionEvent event) {
    }
    
}
