
package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALUnidade;
import db.entidades.Unidade;
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

public class FXMLCADUnidadeController implements Initializable 
{
    @FXML
    private JFXTextField tbNome;
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
    private JFXTextField tbPesquisar;
    @FXML
    private JFXButton BtnPesquisar;
    @FXML
    private TableView<Unidade> tbvDados;
    @FXML
    private TableColumn<Unidade, String> colCod;
    @FXML
    private TableColumn<Unidade, String> colNome;
    @FXML
    private JFXTextField tbCodigo;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {      
        colCod.setCellValueFactory(new PropertyValueFactory("uni_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("uni_nome"));

        estadoOriginal();
    }

    private void estadoOriginal() {
        BtnPesquisar.setDisable(false);
        BtnNovo.setDisable(true);
        pnDados.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        BtnApagar.setDisable(true);
        BtnAlterar.setDisable(true);
        BtnNovo.setDisable(false);
        tbPesquisar.setDisable(false);
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

    private void carregaTabela(String filtro) {
        DALUnidade dal = new DALUnidade();
        List<Unidade> res = dal.get(filtro);
        ObservableList<Unidade> modelo;
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
            Unidade u = (Unidade)tbvDados.getSelectionModel().getSelectedItem();
            tbCodigo.setText("" + u.getUni_id());
            tbNome.setText(u.getUni_nome());
            estadoEdicao();       
        }
    }

    @FXML
    private void clkBtnApagar(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Deseja realmente apagar?"); 
        if(a.showAndWait().get() == ButtonType.OK)
        {
            DALUnidade dal = new DALUnidade();
            a = new Alert(Alert.AlertType.INFORMATION);
            if(dal.apagar(tbvDados.getSelectionModel().getSelectedItem()))
            {
                a.setContentText("Produto deletado com sucesso");
                carregaTabela("");
            }
            else
                a.setContentText("Erro ao deletar produto");
            
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
        
        Unidade u = new Unidade(cod,tbNome.getText());
        DALUnidade dal = new DALUnidade();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(u.getUni_id() == 0)
        {
            if(dal.gravar(u))
                a.setContentText("Unidade gravada com sucesso");
            else
                a.setContentText("Erro ao gravar a unidade");
        }
        else
        {
            if(dal.alterar(u))
                a.setContentText("Unidade alterada com sucesso");
            else 
                a.setContentText("Erro ao alterar a unidade");
        }
        a.showAndWait();
        estadoOriginal();
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) {
        if(!pnDados.isDisable())
            estadoOriginal();
        else
            FXMLPrincipalController.spainelpnprincipal.setCenter(null);
    }

    @FXML
    private void clkBtnPesquisar(ActionEvent event) 
    {
        //carregaTabela("uni_nome = " + tbPesquisar.getText());
        carregaTabela("UPPER(uni_nome) like '%" + tbPesquisar.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event) {
         if(tbvDados.getSelectionModel().getSelectedIndex()>=0)
        {
           BtnAlterar.setDisable(false);
           BtnApagar.setDisable(false);
        }
    }
}
