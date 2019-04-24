package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALProduto;
import db.entidades.Comanda;
import db.entidades.Produto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.MaskFieldUtil;

public class FXMLInsereProdutoController implements Initializable {

    @FXML
    private JFXTextField tbQuantidade;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btInserir;
    @FXML
    private JFXTextField tbNonoe;
    @FXML
    private JFXTextField tbPesquisa;
    @FXML
    private TableView<Produto> tbvDados;
    @FXML
    private TableColumn<Produto, String> colNpme;
    @FXML
    private TableColumn<Produto, String> colPreco;
    @FXML
    private TableColumn<Produto, String> colCod;
    
    private Comanda c = new Comanda();
    private Produto p;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       colNpme.setCellValueFactory(new PropertyValueFactory("prod_nome"));
       colPreco.setCellValueFactory(new PropertyValueFactory("prod_preco"));
       colCod.setCellValueFactory(new PropertyValueFactory("prod_id"));
       
        MaskFieldUtil.numericField(tbQuantidade);
       carregaTabela("");
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
        p = null;
        ((Stage) btCancelar.getScene().getWindow()).close();
    }

    @FXML
    private void clkInserir(ActionEvent event) {
        if(!tbNonoe.getText().isEmpty())
        {
            c.addProduto(p, Integer.parseInt(tbQuantidade.getText()), p.getProd_preco());
            ((Stage)btInserir.getScene().getWindow()).close();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Nenhum produto selecionado");
            a.showAndWait();
        } 
    }

    @FXML
    private void clkPesquisar(ActionEvent event) {
        carregaTabela("UPPER(prod_nome) like '%" + tbPesquisa.getText().toUpperCase() + "%'");
    }
    
    private void carregaTabela(String filtro)
    {
        DALProduto dal = new DALProduto();
        List<Produto> l = dal.get(filtro);
        ObservableList<Produto> ob = FXCollections.observableList(l);
        tbvDados.setItems(ob);
    }

    @FXML
    private void clkTabela(MouseEvent event) {
        if(tbvDados.getSelectionModel().getSelectedIndex() >= 0)
        {
            btInserir.setDisable(false);
            p = (Produto) tbvDados.getSelectionModel().getSelectedItem();
            tbNonoe.setText(p.getProd_nome());
        }
    }
    
    public Comanda.Item getProduto()
    {
        return c.getItens().get(0);
    }
}
