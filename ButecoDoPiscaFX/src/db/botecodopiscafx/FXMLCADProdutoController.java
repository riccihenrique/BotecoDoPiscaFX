package db.botecodopiscafx;

import util.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALCategoria;
import db.dal.DALProduto;
import db.dal.DALUnidade;
import db.entidades.Categoria;
import db.entidades.Produto;
import db.entidades.Unidade;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class FXMLCADProdutoController implements Initializable {

    @FXML
    private JFXTextField tbNome;
    @FXML
    private JFXTextField tbPesquisa;
    @FXML
    private JFXTextField tbPreco;
    @FXML
    private JFXTextField tbDescricao;
    @FXML
    private JFXComboBox<Categoria> cbCategoria;
    @FXML
    private JFXComboBox<Unidade> cbUnidade;
    @FXML
    private JFXTextField tbCodigo;
    @FXML
    private TableView<Produto> tbvDados;
    @FXML
    private JFXButton BtnNovo;
    @FXML
    private JFXButton BtnAlterar;
    @FXML
    private JFXButton BtnApagar;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton BtnCancelar;
    @FXML
    private JFXButton BtnPesquisar;
    @FXML
    private TableColumn<Produto, String> colCod;
    @FXML
    private TableColumn<Produto, String> colNome;
    @FXML
    private TableColumn<Produto, String> colPreco;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private SplitPane splitPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
        colCod.setCellValueFactory(new PropertyValueFactory("prod_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("prod_nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory("prod_preco"));
        
        MaskFieldUtil.monetaryField(tbPreco);
        
        estadoOriginal();
    }
    
    @FXML
    private void clkBtnNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void clkBtnAlterar(ActionEvent event) {
        if(tbvDados.getSelectionModel().getSelectedItem() != null)
        {
            Produto p = (Produto)tbvDados.getSelectionModel().getSelectedItem();
            tbCodigo.setText("" + p.getProd_id());
            tbDescricao.setText(p.getProd_desc());
            tbNome.setText(p.getProd_nome());
            tbPreco.setText(String.format("%10.2f", p.getProd_preco()));
            estadoEdicao();
            cbCategoria.getSelectionModel().select(0);
            cbUnidade.getSelectionModel().select(0);
            cbCategoria.getSelectionModel().select(p.getCat());
            cbUnidade.getSelectionModel().select(p.getUni());
        }
    }

    @FXML
    private void clkBtnApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Deseja realmente apagar?");
        if(a.showAndWait().get() == ButtonType.OK)
        {
            DALProduto dal = new DALProduto();
            a = new Alert(Alert.AlertType.INFORMATION);
            if(dal.apagar(tbvDados.getSelectionModel().getSelectedItem()))
            {
                snackBar("Produto deletado com sucesso");
                carregaTabela("");
            }
            else
            {
                a.setContentText("Erro ao deletar produto");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event)
    {
        if(isOk())
        {
            int cod;
            try
            {
                cod = Integer.parseInt(tbCodigo.getText());
            }
            catch(NumberFormatException e)
            {
                cod = 0;
            }

            Produto p = new Produto(cod, cbCategoria.getValue(), cbUnidade.getValue(), tbNome.getText(), 
                        Double.parseDouble(tbPreco.getText().replace(".", "").replace(',', '.')), tbDescricao.getText());

            DALProduto dal = new DALProduto();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            if(p.getProd_id() == 0)
            {
                if(dal.gravar(p))
                    snackBar("Gravado com sucesso");
                else
                {
                    a.setContentText("Erro ao gravar o produto");
                    a.showAndWait();
                }
            }
            else
            {
                if(dal.alterar(p))
                    snackBar("Alterado com sucesso");
                else
                {
                    a.setContentText("Erro ao alterar o produto");
                    a.showAndWait();
                }
            }

            estadoOriginal();
        }
        
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) {
        if(!pnDados.isDisable())
        {
            estadoOriginal();
        }
        else
        {
            FXMLPrincipalController.efeito(false);
            FXMLPrincipalController.spainelpnprincipal.setCenter(null);
        }
    }

    @FXML
    private void clkBtnPesquisar(ActionEvent event) 
    {
        carregaTabela("UPPER(prod_nome) like '%" + tbPesquisa.getText().toUpperCase() + "%'");
    }

    @FXML 
    private void clkTbPesquisa(KeyEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) 
    {
        if(tbvDados.getSelectionModel().getSelectedIndex()>=0)
        {
           BtnAlterar.setDisable(false);
           BtnApagar.setDisable(false);
        }
    }
    
    private void estadoOriginal() {
        BtnPesquisar.setDisable(false);
        BtnNovo.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        BtnApagar.setDisable(true);
        BtnAlterar.setDisable(true);
        BtnNovo.setDisable(false);
        pnDados.setDisable(true);

        ObservableList<Node> componentes = pnDados.getChildren(); //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
                ((TextInputControl) n).setText("");
            if (n instanceof ComboBox)
                ((ComboBox) n).getItems().clear();
        }
        carregaTabela("");
        carregaComboBox();
    }

    private void carregaComboBox()
    {
        DALCategoria dc = new DALCategoria();
        List <Categoria> dadosCategoria = dc.get("");
        ObservableList <Categoria> obsListCategoria = FXCollections.observableList(dadosCategoria);
        cbCategoria.setItems(obsListCategoria);
        
        DALUnidade du = new DALUnidade();
        List <Unidade> dadosUnidade = du.get("");
        ObservableList <Unidade> obsListUnidade = FXCollections.observableList(dadosUnidade);
        cbUnidade.setItems(obsListUnidade);
    }
    
    private void carregaTabela(String filtro) {
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.get(filtro);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tbvDados.setItems(modelo);
    }
    
    private void estadoEdicao()
    {     
        tbPesquisa.setDisable(true);
        pnDados.setDisable(false);
        BtnConfirmar.setDisable(false);
        BtnApagar.setDisable(true);
        BtnAlterar.setDisable(true);
        tbNome.requestFocus();  
   }
    
    private void snackBar(String texto)
    {
        JFXSnackbar snacbar = new JFXSnackbar(pnDados);
        JFXSnackbarLayout layout = new JFXSnackbarLayout(texto);
        layout.setStyle("-fx-backgroundcolor:#FFFFF");
        snacbar.fireEvent(new JFXSnackbar.SnackbarEvent(layout));
    }
    
    private void fadeout()
    {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), splitPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play(); 
    }
    
    private boolean isOk()
    {
        ObservableList<Node> componentes = pnDados.getChildren();
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
