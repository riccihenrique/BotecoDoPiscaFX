
package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALProduto;
import db.dal.DALTipoPagto;
import db.entidades.Produto;
import db.entidades.TipoPagto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLCADTpPagamentoController implements Initializable 
{

    @FXML
    private JFXTextField tbNome;
    @FXML
    private JFXTextField tbPesquisar;
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
    private AnchorPane pnDados;
    @FXML
    private JFXButton BtnPesquisar;
    @FXML
    private TableView<TipoPagto> tbvDados;
    @FXML
    private TableColumn<TipoPagto, String> colCod;
    @FXML
    private TableColumn<TipoPagto, String> colNome;
    @FXML
    private JFXTextField tbCodigo;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    //preparando as colunas        
        colCod.setCellValueFactory(new PropertyValueFactory("tpg_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("tpg_nome"));

        estadoOriginal();
    }

    private void estadoEdicao()
    {     // carregar os componentes da tela (listbox, combobox, ...)
          // p.e. : carregaEstados();
          BtnNovo.setDisable(true);  
          tbPesquisar.setDisable(true);
          pnDados.setDisable(false);
          BtnConfirmar.setDisable(false);
          BtnApagar.setDisable(true);
          BtnAlterar.setDisable(true);
          tbNome.setDisable(false);
          tbNome.requestFocus(); 
     }
    
    private void estadoOriginal() {
        BtnPesquisar.setDisable(false);
        tbPesquisar.setDisable(false);
        BtnNovo.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        BtnApagar.setDisable(true);
        BtnAlterar.setDisable(true);
        BtnNovo.setDisable(false);
        tbCodigo.setDisable(true);
        tbNome.setDisable(true);

        ObservableList<Node> componentes = pnDados.getChildren(); //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
                ((TextInputControl) n).setText("");
            if (n instanceof ComboBox)
                ((ComboBox) n).getItems().clear();
        }

        carregaTabela("");
    }

    private void carregaTabela(String filtro) {
        DALTipoPagto dal = new DALTipoPagto();
        List<TipoPagto> res = dal.get(filtro);
        ObservableList<TipoPagto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tbvDados.setItems(modelo);
    }

    @FXML
    private void clkBtnNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void clkBtnAlterar(ActionEvent event) {
        
        if(tbvDados.getSelectionModel().getSelectedItem() != null)
        {
            TipoPagto tp = (TipoPagto)tbvDados.getSelectionModel().getSelectedItem();
            tbCodigo.setText("" + tp.getTpg_id());
            tbNome.setText(tp.getTpg_nome());
            estadoEdicao();       
        }
    }

    @FXML
    private void clkBtnApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Deseja realmente apagar?"); 
        if(a.showAndWait().get() == ButtonType.OK)
        {
            DALTipoPagto dal = new DALTipoPagto();
            a = new Alert(Alert.AlertType.INFORMATION);
            if(dal.apagar(tbvDados.getSelectionModel().getSelectedItem()))
            {
                a.setContentText("Tipo de pagamento deletado com sucesso");
                carregaTabela("");
            }
            else
                a.setContentText("Erro ao deletar tipo de pagamento");
            
            a.showAndWait();
        }
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event) {
        int cod;
        try
        {cod = Integer.parseInt(tbCodigo.getText());}
        catch(Exception e)
        {cod = 0;}
        
        TipoPagto tp = new TipoPagto(cod, tbNome.getText());
        DALTipoPagto dal = new DALTipoPagto();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(tp.getTpg_id() == 0)
        {
            if(dal.gravar(tp))
                a.setContentText("Tipo de pagamento gravado com sucesso");
            else
                a.setContentText("Erro ao gravar o tipo de pagamento");
        }
        else
        {
            if(dal.alterar(tp))
                a.setContentText("Tipo de pagamento alterado com sucesso");
            else 
                a.setContentText("Erro ao alterar o tipo de pagamento");
        }
        a.showAndWait();
        estadoOriginal();  
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) {
        estadoOriginal();
    }

    @FXML
    private void clkBtnPesquisar(ActionEvent event) {
        carregaTabela("UPPER(tpg_nome) like '%" + tbPesquisar.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTbvDados(MouseEvent event) {
        if(tbvDados.getSelectionModel().getSelectedIndex() >=0)
        {
            BtnAlterar.setDisable(false);
            BtnApagar.setDisable(false);
        }
    }
}
