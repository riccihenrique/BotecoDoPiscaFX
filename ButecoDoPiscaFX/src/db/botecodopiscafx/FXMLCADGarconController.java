package db.botecodopiscafx;

import Util.BuscaCep;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALGarcon;
import db.entidades.Garcon;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    private JFXTextField tbCodigo;
    @FXML
    private JFXButton BtnPesquisar;
    @FXML
    private TableView<Garcon> tbvDados;
    @FXML
    private TableColumn<Garcon, String> colCod;
    @FXML
    private TableColumn<Garcon, String> colNome;
    @FXML
    private TableColumn<Garcon, String> colTelefone;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        colCod.setCellValueFactory(new PropertyValueFactory("gar_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("gar_nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("gar_fone"));

        estadoOriginal();
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
        DALGarcon dal = new DALGarcon();
        List<Garcon> res = dal.get(filtro);
        ObservableList<Garcon> modelo;
        modelo = FXCollections.observableArrayList(res);
        tbvDados.setItems(modelo);
    }

    @FXML
    private void clkBtnNovo(ActionEvent event) {
        //estadoEdicao();
        
        String jsonResult = BuscaCep.consultaCep("19045380");
        
        System.out.println(jsonResult);
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
