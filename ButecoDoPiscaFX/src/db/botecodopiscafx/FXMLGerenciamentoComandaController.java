package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALComanda;
import db.entidades.Comanda;
import db.entidades.Garcon;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class FXMLGerenciamentoComandaController implements Initializable {

    @FXML
    private JFXButton BtnAlterar;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton BtnCancelar;
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
    
    private Comanda c;
    @FXML
    private AnchorPane pnDados1;
    @FXML
    private AnchorPane pnDados2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colCod.setCellValueFactory(new PropertyValueFactory("prod_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("prod_nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory("prod_preco"));
        colQtde.setCellValueFactory(new PropertyValueFactory("ite_quantidade"));
        
        colCod1.setCellValueFactory(new PropertyValueFactory("pag_id"));
        colTipo.setCellValueFactory(new PropertyValueFactory("tpg_nome"));
        colValor.setCellValueFactory(new PropertyValueFactory("pag_valor"));
        
        carregaTabela();
        carregaDados();
        estadoOriginal();
    } 
    
    private void carregaTabela()
    {
        ObservableList<Comanda.Item> obItem = FXCollections.observableList(c.getItens());
        tbvItens.setItems(obItem);
        ObservableList<Comanda.Pagamento> obPagamento = FXCollections.observableList(c.getPagamentos());
        tbvPagamentos.setItems(obPagamento);
    }
    
    private void estadoOriginal()
    {
        pnDados2.setDisable(true);
        pnDados1.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnAlterar.setDisable(false);
        BtnFechar.setDisable(false);
        BtnFechar.setDisable(false);
    }
    
    private void estadoEdicao()
    {
        pnDados2.setDisable(false);
        pnDados1.setDisable(false);
        BtnConfirmar.setDisable(false);
        BtnAlterar.setDisable(true);
        BtnFechar.setDisable(false);
        BtnFechar.setDisable(false);
    }
    
    private void carregaDados()
    {
        tbDescricao.setText(c.getCom_desc());
        tbMesa.setText("" + c.getCom_numero());
        tbNome.setText(c.getCom_nome());
        double valor = 0;
        for(Comanda.Item ci : c.getItens())
            valor += ci.getIt_valor();
        
        tbValor.setText("" + valor);
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
    
    public void setComanda(Comanda c)
    {
        this.c = c;
    }
}
