package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import db.banco.Banco;
import db.dal.DALComanda;
import db.dal.DALGarcon;
import db.entidades.Comanda;
import db.entidades.Garcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.MaskFieldUtil;

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
    @FXML
    private AnchorPane pnDados1;
    @FXML
    private AnchorPane pnDados2;
    @FXML
    private JFXButton btnRemoverItem;
    @FXML
    private JFXButton btnRemoverPag;
    
    private Comanda c;
    private double valor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colNome.setCellValueFactory(new PropertyValueFactory("prod"));
        colPreco.setCellValueFactory(new PropertyValueFactory("it_preco"));
        colQtde.setCellValueFactory(new PropertyValueFactory("it_quantidade"));
        
        colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colValor.setCellValueFactory(new PropertyValueFactory("pag_valor"));
        
        MaskFieldUtil.monetaryField(tbValor);
    } 

    @FXML
    private void clkBtnAlterar(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void clkTabelaItens(MouseEvent event) {
        if(tbvItens.getSelectionModel().getSelectedItem() != null)
        {
            btnRemoverItem.setDisable(false);
        }
    }

    @FXML
    private void clkTabelaPagamentos(MouseEvent event) {
        if(tbvPagamentos.getSelectionModel().getSelectedItem() != null)
        {
            btnRemoverPag.setDisable(false);
        }
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event)
    {
        if(isOk())
        {
            DALComanda dal = new DALComanda();
            if(dal.alterar(c))
            {
                snackBar("Comanda alterada com sucesso");
                estadoOriginal();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Erro ao alterar comanda. " + Banco.getCon().getMensagemErro());
                a.showAndWait();
            } 
        }
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) throws IOException {
        if(!pnDados1.isDisable())
            estadoOriginal();
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPainelComanda.fxml"));
            FXMLPrincipalController.spainelpnprincipal.setCenter(root);
        }
    }

    @FXML
    private void clkBtnFechar(ActionEvent event) throws IOException, InterruptedException 
    {
        if(valor == 0)
        {
            c.setCom_status('F');
            DALComanda dal = new DALComanda();
            if(dal.alterar(c))
            {
                String sql = "select * from comanda c join item i on i.com_id = c.com_id join pagamento p on p.com_id = c.com_id join produto pr on pr.prod_id = i.prod_id join tipopgto tp on p.tpg_id = tp.tpg_id where c.com_id = " + c.getCom_id();
                FXMLPrincipalController.gerarRelatorioIntegrado(sql, "rel/nota_fiscal.jasper", null, null);
                //clkBtnCancelar(event);
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Erro ao fechar comanda");
                a.showAndWait();
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Ainda há valor a ser quitado");
            a.showAndWait();
        }
    }
    
    @FXML
    private void clkBtnInserirItem(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInsereProduto.fxml"));
        Parent root = (Parent) loader.load();
        FXMLInsereProdutoController p = loader.getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        Comanda.Item ite = p.getProduto();
        c.addProduto(ite.getProd(), ite.getIt_quantidade(), ite.getIt_preco());
        alteraValor();
        carregaTabela();
        
    }

    @FXML
    private void clkBtnRemoverItem(ActionEvent event) {
        if(tbvItens.getSelectionModel().getSelectedItem() != null)
        {
            c.getItens().remove(tbvItens.getSelectionModel().getSelectedItem());
            alteraValor();
            carregaTabela();
        }
    }

    @FXML
    private void clkBtnInserirPagamento(ActionEvent event) throws IOException {
        if(valor > 0)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInserePagamento.fxml"));
            Parent root = (Parent) loader.load();
            FXMLInserePagamentoController p = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            Comanda.Pagamento pg = p.getPgto();
            c.addPagamento(pg.getPag_valor(), pg.getTipo());
            alteraValor();
            carregaTabela();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Não há mais nada para pagar");
            a.show();
        }
    }

    @FXML
    private void clkBtnRemoverPagamento(ActionEvent event) {
        if(tbvPagamentos.getSelectionModel().getSelectedItem() != null)
        {
           c.getPagamentos().remove(tbvPagamentos.getSelectionModel().getSelectedItem());
           carregaTabela();
           alteraValor();
        }
    }
    
    public Comanda getComanda()
    {
        return c;
    }
    
    public void setComanda(Comanda c)
    {
        this.c = c;
        carregaTabela();
        carregaDados();
        estadoOriginal();
    }
    
    private void snackBar(String texto)
    {
      JFXSnackbar snacbar = new JFXSnackbar(pnDados2);
        JFXSnackbarLayout layout = new JFXSnackbarLayout(texto);
        layout.setStyle("-fx-backgroundcolor:#FFFFF");
        snacbar.fireEvent(new JFXSnackbar.SnackbarEvent(layout));
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
        btnRemoverItem.setDisable(true);
        btnRemoverPag.setDisable(true);
        pnDados2.setDisable(true);
        pnDados1.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnAlterar.setDisable(false);
        BtnFechar.setDisable(false);
        BtnFechar.setDisable(false);
    }
    
    private void estadoEdicao()
    {
        btnRemoverItem.setDisable(true);
        btnRemoverPag.setDisable(true);
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
        DALGarcon dal = new DALGarcon();
        ObservableList<Garcon> ob = FXCollections.observableList(dal.get(""));
        cbGarcon.setItems(ob);
        cbGarcon.getSelectionModel().select(0);
        cbGarcon.getSelectionModel().select(c.getGarcon());
        dtData.setValue(c.getCom_data());
        alteraValor();
    }
    
    private void alteraValor()
    {
        valor = 0;
        double val = 0;
        for(Comanda.Item ci : c.getItens())
            valor += ci.getIt_preco() * ci.getIt_quantidade();
        c.setCom_valor(valor);
        for(Comanda.Pagamento p : c.getPagamentos())
            val += p.getPag_valor();
        
        valor = valor - val;
        tbValor.setText(String.format("%10.2f", valor));
    }
    private boolean isOk()
    {
        ObservableList<Node> componentes = pnDados2.getChildren();
        for (Node n : componentes) {
            if (n instanceof TextInputControl &&  ((TextInputControl) n).getText().isEmpty())
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("O campo " + n.getId().replace("tb", "") + " não foi preenchido");
                if(!n.getId().equals("tbCodigo"))
                {
                    a.show();
                    return false;
                }
            }
            if (n instanceof ComboBox && ((ComboBox) n).getSelectionModel().getSelectedItem()== null)
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("O campo " + n.getId().replace("cb", "") + " não foi selecionado");
                a.show();
                return false;
            }
        }
        return true;
    }
}
