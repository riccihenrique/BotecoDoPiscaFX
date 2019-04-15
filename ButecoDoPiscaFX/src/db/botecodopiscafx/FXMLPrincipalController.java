package db.botecodopiscafx;

import db.banco.Banco;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class FXMLPrincipalController implements Initializable 
{    
    private Label label;
    @FXML
    private BorderPane painelpnprincipal;
    
    public static BorderPane spainelpnprincipal = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        spainelpnprincipal = painelpnprincipal;
        efeito(false);
    }
    
    @FXML
    private void clkCadProduto(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADProduto.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
         
    }  

    @FXML
    private void clkCadGarcon(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADGarcon.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadUnidade(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADUnidade.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadCategoria(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADCategoria.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkCadTpPagamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCADTpPagamento.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkAbrir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLComandaAbrir.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }

    @FXML
    private void clkGerenciar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPainelComanda.fxml"));
        efeito(true);
        painelpnprincipal.setCenter(root);
    }
    
    public static void efeito(boolean on)
    {
        if(on)
        {
//            FadeTransition ft = new FadeTransition(Duration.millis(500), spnprincipal);
//            ft.setFromValue(1.0);
//            ft.setToValue(0.5);
//            ft.play(); 
              spainelpnprincipal.setStyle("-fx-background-image: url('icons/textura2.png');");

        }
        else
            spainelpnprincipal.setStyle("-fx-background-image: url('icons/textura.png');");
            
    }

    @FXML
    private void clkLink(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("http://unoeste.br"));
        } catch (Exception ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clkBtnHome(ActionEvent event) {
        painelpnprincipal.setCenter(null);
        efeito(false);
    }
    
    @FXML
    private void clkRelProd(ActionEvent event) {
        String sql = "SELECT p.prod_id,p.prod_nome,c.cat_nome,p.prod_descr,p.prod_preco FROM produto p JOIN categoria c ON  p.cat_id = c.cat_id ORDER BY p.prod_nome";
        gerarRelatorioIntegrado(sql, "rel/Simple_Blue.jasper");
    }
    
    private void gerarRelatorio(String sql, String relat, String titulotela)
    {
        try {  //sql para obter os dados para o relatorio
            ResultSet rs = Banco.getCon().consultar(sql);
            //implementação da interface JRDataSource para DataSource ResultSet
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            //preenchendo e chamando o relatório
            String jasperPrint = JasperFillManager.fillReportToFile(relat, null, jrRS);
            JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
            
            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
            viewer.setTitle(titulotela);
            viewer.setVisible(true);

        } catch (JRException erro) {
            System.out.println(erro);
        }
    } 
    private void gerarRelatorioIntegrado(String sql, String relat)
    {
        try {  //sql para obter os dados para o relatorio
            ResultSet rs = Banco.getCon().consultar(sql);
            //implementação da interface JRDataSource para DataSource ResultSet
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            //preenchendo e chamando o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(relat, null, jrRS);
            
            /*
            CORRIGIR as linhas 166 e 167
            Criar um rel simples ou por agrupamento (categoria, garçon e unidade)
            garçon - ordem alfabética
            */
            
            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setOpaque(true);
            viewer.setVisible(true);
            viewer.setZoomRatio(1f);
            
            SwingNode sn = new SwingNode();
            SwingUtilities.invokeLater(()->{sn.setContent(viewer);});
            painelpnprincipal.setCenter(sn);
            

        } catch (JRException erro) {
            System.out.println(erro);
        }
    } 

    @FXML
    private void clkRelProdCat(ActionEvent event) {
        String sql = "SELECT p.prod_id,p.prod_nome,c.cat_nome,p.prod_descr,p.prod_preco FROM produto p JOIN categoria c ON  p.cat_id = c.cat_id ORDER BY p.prod_nome";
        gerarRelatorioIntegrado(sql, "rel/Simple_Blue.jasper");
    }

    @FXML
    private void clkRelGarCid(ActionEvent event) {
        String sql = "select gar_cidade, gar_nome from garcon order by gar_cidade";
        gerarRelatorioIntegrado(sql, "rel/rel_garcon.jasper");
        //aaaaa
    }

    @FXML
    private void clkRelCat(ActionEvent event) {
    }

    @FXML
    private void clkRelUni(ActionEvent event) {
    }
}
