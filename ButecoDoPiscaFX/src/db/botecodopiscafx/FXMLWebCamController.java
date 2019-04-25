package db.botecodopiscafx;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLWebCamController implements Initializable {

    private ImageView imgCamera;
    public static Image img;
    
    private Webcam webcam;
    private JFXComboBox<Webcam> cbWebCam;
    private JFXButton btnCapturar;
    @FXML
    private JFXDatePicker dtPickerIni;
    @FXML
    private JFXDatePicker dtPickerFim;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try
        {
            ObservableList <Webcam> obsListCameras = FXCollections.observableList(Webcam.getWebcams());
            cbWebCam.setItems(obsListCameras);
            cbWebCam.getSelectionModel().select(0);
            new Thread(new Task<Void>() 
            {
                @Override
                protected Void call() throws Exception 
                {
                    ///Platform.runLater(()->{
                    //btnCapturar.getScene().setCursor(Cursor.WAIT);
                    //});
                    webcam = cbWebCam.getValue();
                    webcam.setViewSize(new Dimension(320,240));
                    webcam.open();
                    
                    BufferedImage bimage;
                    WritableImage wimg;
                    while(!btnCapturar.isArmed())
                    {
                        bimage = webcam.getImage();

                        //opcionalmente salvar em arquivo

                        //Converter em Image (JavaFX)
                        wimg=SwingFXUtils.toFXImage(bimage,null);

                        // aplicar no componente ImageView
                        imgCamera.setImage(wimg);
                    }
                    //Platform.runLater(()->{
                     //     btnCapturar.getScene().setCursor(Cursor.DEFAULT);
                    //});
                     return null;
                }
            }).start();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro: WebCam não encontrada");
            System.exit(-1);
        }
    }    

    private void clkFoto(ActionEvent event) 
    {
        BufferedImage bimage;
        WritableImage wimg;
        
        bimage = webcam.getImage();

        //opcionalmente salvar em arquivo

        //Converter em Image (JavaFX)
        wimg=SwingFXUtils.toFXImage(bimage,null);

        // aplicar no componente ImageView
        imgCamera.setImage(wimg);
        // obter a imagem
    }

    private void clkCbWeb(ActionEvent event) 
    {
        new Thread(new Task<Void>() 
        {
            @Override
            protected Void call() throws Exception 
            {
                //Platform.runLater(()->{
                //btnCapturar.getScene().setCursor(Cursor.WAIT);
               // });
                webcam = cbWebCam.getValue();
                webcam.setViewSize(new Dimension(320,240));
                webcam.open();
                //Platform.runLater(()->{
                //      btnCapturar.getScene().setCursor(Cursor.DEFAULT);
                //});
                 return null;
            }
        }).start();
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        img = null;
        ((Stage) btnCapturar.getScene().getWindow()).close();
    }

    @FXML
    private void clkConfirmar(ActionEvent event) {
        img = imgCamera.getImage();
        ((Stage) btnCapturar.getScene().getWindow()).close();
    }
}
