
package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALTipoPagto;
import db.entidades.TipoPagto;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
    @FXML
    private SplitPane splitPane;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {      
        fadeout();
        colCod.setCellValueFactory(new PropertyValueFactory("tpg_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("tpg_nome"));

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
                snackBar("Tipo de pagamento deletado com sucesso");
                carregaTabela("");
            }
            else
            {
                a.setContentText("Erro ao deletar tipo de pagamento");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event) {
        if(isOk())
        {
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
                    snackBar("Tipo de pagamento gravado com sucesso");
                else
                {
                    a.setContentText("Erro ao gravar o tipo de pagamento");
                    a.showAndWait();
                }
            }
            else
            {
                if(dal.alterar(tp))
                    snackBar("Tipo de pagamento alterado com sucesso");
                else 
                {
                    a.setContentText("Erro ao alterar o tipo de pagamento");
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
    
    private void snackBar(String texto)
    {
        JFXSnackbar snacbar = new JFXSnackbar(pnDados);
        JFXSnackbarLayout layout = new JFXSnackbarLayout(texto);
        layout.setStyle("-fx-backgroundcolor:#FFFFF");
        snacbar.fireEvent(new JFXSnackbar.SnackbarEvent(layout));
    }

    private void estadoEdicao()
    {     
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
        pnDados.setDisable(true);
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
