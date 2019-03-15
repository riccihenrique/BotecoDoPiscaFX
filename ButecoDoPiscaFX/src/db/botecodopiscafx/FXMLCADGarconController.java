package db.botecodopiscafx;

import Util.BuscaCep;
import Util.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.dal.DALGarcon;
import db.entidades.Garcon;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.json.JSONObject;

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
    @FXML
    private ImageView imgvFoto;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        colCod.setCellValueFactory(new PropertyValueFactory("gar_id"));
        colNome.setCellValueFactory(new PropertyValueFactory("gar_nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("gar_fone"));
        
        MaskFieldUtil.cepField(tbCep);
        MaskFieldUtil.cpfCnpjField(tbCpf);
        MaskFieldUtil.foneField(tbTelefone);
        
        tbCep.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if(!newValue)
                {
                    String json = BuscaCep.consultaCep(tbCep.getText().replace("-", ""));
                    Platform.runLater(()-> {
                        JSONObject ob = new JSONObject(json);
                        tbCidade.setText(ob.getString("localidade"));
                        tbEndereco.setText(ob.getString("logradouro"));
                        tbUf.setText(ob.getString("uf"));
                    });
                }
             }
        });

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

    private void estadoEdicao()
    {   
          BtnNovo.setDisable(true);  
          tbPesquisa.setDisable(true);
          pnDados.setDisable(false);
          BtnConfirmar.setDisable(false);
          BtnApagar.setDisable(true);
          BtnAlterar.setDisable(true);
          tbNome.setDisable(false);
          tbNome.requestFocus(); 
     }
    
    @FXML
    private void clkBtnNovo(ActionEvent event) {
        estadoEdicao();
    }

    @FXML
    private void clkBtnAlterar(ActionEvent event)
    {
        if(tbvDados.getSelectionModel().getSelectedItem() != null)
        {
            Garcon g = (Garcon)tbvDados.getSelectionModel().getSelectedItem();
            tbCodigo.setText("" + g.getGar_id());
            tbNome.setText(g.getGar_nome());
            tbCep.setText(g.getGar_cep());
            tbCidade.setText(g.getGar_cidade());
            tbCpf.setText(g.getGar_cpf());
            tbEndereco.setText(g.getGar_endereco());
            tbTelefone.setText(g.getGar_fone());
            tbUf.setText(g.getGar_uf());
            DALGarcon dal = new DALGarcon();
            estadoEdicao();       
        }
    }

    @FXML
    private void clkBtnApagar(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Deseja realmente apagar?"); 
        if(a.showAndWait().get() == ButtonType.OK)
        {
            DALGarcon dal = new DALGarcon();
            a = new Alert(Alert.AlertType.INFORMATION);
            if(dal.apagar(tbvDados.getSelectionModel().getSelectedItem()))
            {
                a.setContentText("Garçon deletado com sucesso");
                carregaTabela("");
            }
            else
                a.setContentText("Erro ao deletar garçon");
            
            a.showAndWait();
        }
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event) throws IOException
    {
        int cod;
        try
        {
            cod = Integer.parseInt(tbCodigo.getText());
        }
        catch(Exception e)
        {
            cod = 0;
        }
        
        Garcon g = new Garcon(cod, tbNome.getText(), tbCep.getText(), tbCpf.getText(),
            tbEndereco.getText() , tbCidade.getText(), tbUf.getText(), tbTelefone.getText());
        DALGarcon dal = new DALGarcon();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        if(g.getGar_id()== 0)
        {
            if(dal.gravar(g))
            {
                a.setContentText("Categoria gravada com sucesso");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage) imgvFoto.getImage(),"png", os); 
                InputStream fis = new ByteArrayInputStream(os.toByteArray());
                dal.gravarFoto(g, (FileInputStream) fis);
            }
            else
                a.setContentText("Erro ao gravar a categoria");
        }
        else
        {
            if(dal.alterar(g))
            {
                a.setContentText("Categoria alterada com sucesso");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage) imgvFoto.getImage(),"png", os); 
                InputStream fis = new ByteArrayInputStream(os.toByteArray());
                dal.gravarFoto(g, (FileInputStream) fis);
            }
            else 
                a.setContentText("Erro ao alterar a categoria");
        }
        a.showAndWait();
        estadoOriginal();
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) 
    {
        if(!pnDados.isDisable())
        {
            estadoOriginal();
        }
        else
        {
            FXMLPrincipalController.spainelpnprincipal.setCenter(null);
        }
    }

    @FXML
    private void clkBtnPesquisar(ActionEvent event) 
    {
        carregaTabela("UPPER(gar_nome) like '%" + tbPesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtnFoto(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLWebCam.fxml"));
        Scene cena = new Scene(root);
        Stage sta = new Stage();
        sta.setScene(cena);
        sta.show();
    }

    @FXML
    private void clkBtnLocalizar(ActionEvent event) 
    {
        FileChooser arquivo = new FileChooser();
        File arq = arquivo.showOpenDialog(null);
        imgvFoto.setImage(new Image(arq.toURI().toString()));
    }
    
    @FXML
    private void clkTabela(MouseEvent event) 
    {
        if(tbvDados.getSelectionModel().getSelectedIndex() >= 0)
        {
            BtnApagar.setDisable(false);
            BtnAlterar.setDisable(false);
        }
    }
}
