package db.botecodopiscafx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALCategoria;
import db.dal.DALTipoPagto;
import db.entidades.Categoria;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private JFXTextField tbCódigo;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //preparando as colunas        
        colCod.setCellValueFactory(new PropertyValueFactory("cat_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("cat_nome"));

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
        DALCategoria dal = new DALCategoria();
        List<Categoria> res = dal.get(filtro);
        ObservableList<Categoria> modelo;
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
}
