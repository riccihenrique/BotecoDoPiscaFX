
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private JFXTextField tbCódigo;
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
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    //preparando as colunas        
        colCod.setCellValueFactory(new PropertyValueFactory("uni_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("uni_nome"));

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
        DALUnidade dal = new DALUnidade();
        List<Unidade> res = dal.get(filtro);
        ObservableList<Unidade> modelo;
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
