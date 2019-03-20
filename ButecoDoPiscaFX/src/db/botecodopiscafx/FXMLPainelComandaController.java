package db.botecodopiscafx;

import db.dal.DALComanda;
import db.entidades.Comanda;
import java.io.IOException;
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
                if(c.getPagamentos().isEmpty())
                    ctl.setCor("#938d99");
                else
                    ctl.setCor("#a675a1"); //Alterar cor daqui
                painel.getChildren().add(root);
            } 
        }
        catch(IOException e )
        {
            System.out.println(e.getMessage());
        }
    }
}
