package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALCategoria;
import db.entidades.Categoria;
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

public class FXMLCADCategoriaController implements Initializable
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
    private TableView<Categoria> tbvDados;
    @FXML
    private TableColumn<Categoria, String> colCod;
    @FXML
    private TableColumn<Categoria, String> colNome;
    @FXML
    private JFXTextField tbCodigo;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {     
        colCod.setCellValueFactory(new PropertyValueFactory("cat_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("cat_nome"));

        estadoOriginal();
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
        tbPesquisar.setDisable(false);
        BtnPesquisar.setDisable(false);
        BtnNovo.setDisable(true);
        pnDados.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        BtnApagar.setDisable(true);
        BtnAlterar.setDisable(true);
        tbCodigo.setDisable(true);
        tbNome.setDisable(true);
        BtnNovo.setDisable(false);

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
        DALCategoria dal = new DALCategoria();
        List<Categoria> res = dal.get(filtro);
        ObservableList<Categoria> modelo;
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
            Categoria c = (Categoria)tbvDados.getSelectionModel().getSelectedItem();
            tbCodigo.setText("" + c.getCat_id());
            tbNome.setText(c.getCat_nome());
            estadoEdicao();       
        }
    }

    @FXML
    private void clkBtnApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Deseja realmente apagar?"); 
        if(a.showAndWait().get() == ButtonType.OK)
        {
            DALCategoria dal = new DALCategoria();
            a = new Alert(Alert.AlertType.INFORMATION);
            if(dal.apagar(tbvDados.getSelectionModel().getSelectedItem()))
            {
                a.setContentText("Categoria deletada com sucesso");
                carregaTabela("");
            }
            else
                a.setContentText("Erro ao deletar categoria");
            
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
        
        Categoria c = new Categoria(cod, tbNome.getText());
        DALCategoria dal = new DALCategoria();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(c.getCat_id() == 0)
        {
            if(dal.gravar(c))
                a.setContentText("Categoria gravada com sucesso");
            else
                a.setContentText("Erro ao gravar a categoria");
        }
        else
        {
            if(dal.alterar(c))
                a.setContentText("Categoria alterada com sucesso");
            else 
                a.setContentText("Erro ao alterar a categoria");
        }
        a.showAndWait();
        estadoOriginal();  
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
        carregaTabela("UPPER(cat_nome) like '%" + tbPesquisar.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTbvDados(MouseEvent event) {
        if(tbvDados.getSelectionModel().getSelectedIndex() >= 0)
        {
            BtnApagar.setDisable(false);
            BtnAlterar.setDisable(false);
        }
    }
}
