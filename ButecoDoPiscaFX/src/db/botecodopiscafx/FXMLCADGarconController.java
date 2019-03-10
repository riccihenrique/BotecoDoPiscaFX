package db.botecodopiscafx;

import com.jfoenix.controls.JFXTextField;
import db.dal.DALProduto;
import db.entidades.Produto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;

public class FXMLCADGarconController implements Initializable 
{

    @FXML
    private JFXTextField tbNome;
    @FXML
    private JFXTextField tbCpf;
    @FXML
    private JFXTextField tbCep;
    @FXML
    private JFXTextField tbEndereco;
    @FXML
    private JFXTextField tbTelefone;
    @FXML
    private JFXTextField tbCidade;
    @FXML
    private JFXTextField tbUf;
    @FXML
    private JFXTextField tbPesquisa;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
    
    private void estadoOriginal() {
        BtnPesquisar.setDisable(false);
        BtnNovo.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        BtnApagar.setDisable(true);
        BtnAlterar.setDisable(true);
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
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.get(filtro);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tbvDados.setItems(modelo);
    }

    @FXML
    private void clkBtnNovo(ActionEvent event) {
    }

    @FXML
    private void clkBtnAlterar(ActionEvent event) {
    }

    @FXML
    private void clkBtnApagar(ActionEvent event) {
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event) {
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void clkBtnPesquisar(ActionEvent event) {
    }

    @FXML
    private void clkBtnFoto(ActionEvent event) {
    }
}
