package db.botecodopiscafx;

import db.banco.Banco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class BotecoDoPiscaFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) 
    {
        if(!Banco.conectar())
        {
            JOptionPane.showMessageDialog(null, "Erro: " + Banco.getCon().getMensagemErro());
            if(JOptionPane.showConfirmDialog(null, "Deseja criar uma base de dados?") == JOptionPane.YES_OPTION)
            {
                if(!Banco.criarBD("boteco"))
                    JOptionPane.showMessageDialog(null, "Erro ao criar banco: " + Banco.getCon().getMensagemErro());
                else
                    if(!Banco.criarTabelas("script/script.txt", "boteco"))
                        JOptionPane.showMessageDialog(null, "Erro ao criar tabelas: " + Banco.getCon().getMensagemErro());
                    else
                        JOptionPane.showMessageDialog(null, "Tudo certo consagrado");
                
            }    
            else
                System.exit(-1);
        }
        
        //Banco.realizaBackup("restore");
        launch(args);
    }
}
