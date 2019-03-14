package db.botecodopiscafx;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

public class FXMLWebCamController implements Initializable {

    @FXML
    private ImageView imgCamera;
    private Webcam webcam;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try
        {
            webcam = Webcam.getDefault();
            webcam.setViewSize(new Dimension(320,240));
            webcam.open();
        }
        catch(Exception e)
        {
            System.out.println("");
        }
    }    

    @FXML
    private void clkFoto(ActionEvent event) 
    {
        // obter a imagem
        BufferedImage bimage;
        bimage = webcam.getImage();

        //opcionalmente salvar em arquivo

        //Converter em Image (JavaFX)
        WritableImage wimg;
        wimg=SwingFXUtils.toFXImage(bimage,null);

        // aplicar no componente ImageView
        imgCamera.setImage(wimg);

    }
}
