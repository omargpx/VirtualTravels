package virtualtravels;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class AcercaDeController implements Initializable {
    
    @FXML
    private Label lblFecha;
    @FXML
    private AnchorPane ap;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblFecha.setText(fechaActual());//MOSTRANDO FECHA ACTUAL EN UN LABEL
    }
    public static String fechaActual(){ // CAPTURA DE LA FECHA ACTUAL
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }

    @FXML
    private void btnPreguntasFrecuentes(ActionEvent event) throws IOException {
        AnchorPane soporte = FXMLLoader.load(getClass().getResource("/virtualtravels/acercaDe.fxml"));
        ap.getChildren().setAll(soporte);
    }
    
}
