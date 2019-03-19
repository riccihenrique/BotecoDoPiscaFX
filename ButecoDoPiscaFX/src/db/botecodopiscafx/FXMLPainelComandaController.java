/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.botecodopiscafx;

import db.dal.DALComanda;
import db.entidades.Comanda;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.TilePane;

public class FXMLPainelComandaController implements Initializable {

    @FXML
    private TilePane painel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DALComanda dal = new DALComanda();
        List<Comanda> lista = dal.get("com_status = 'A'");
        try
        {
            for(Comanda c : lista)
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTileComanda.fxml"));
                Parent root = (Parent) loader.load();
                FXMLTileComandaController ctl = loader.getController();
                ctl.setComanda(c);
                painel.getChildren().add(root);
            } 
        }
        catch(Exception e )
        {
            System.out.println(e.getMessage());
        }
    }
}
