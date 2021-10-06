/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import virtualtravels.Conexion;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class ComunicadosController implements Initializable {

    @FXML
    private ComboBox<String> cbxEstado;
    @FXML
    private TextArea txtMensaje;
    @FXML
    private TextField txtTitulo;
    @FXML
    private Label lblID;
    @FXML
    private AnchorPane ap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> tipNotf = FXCollections.observableArrayList("Emergente", "Normal");
        cbxEstado.setItems(tipNotf);
        lblID.setText("");
    }

    public static String fechaActual() { // CAPTURA DE LA FECHA ACTUAL
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }

    public void executeQuery(String query) {
        Conexion conex = new Conexion();
        Connection conn = conex.getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnEnviarComunicado(ActionEvent event) {
        String query = "UPDATE notificaciones SET id='" + lblID.getText() + "', titulo ="
                + "'" + txtTitulo.getText() + "', mensaje ="
                + "'" + txtMensaje.getText() + "', fecha ="
                + "'" + fechaActual() + "' where estado = '" + cbxEstado.getValue() + "';";
        executeQuery(query);
    }

    @FXML
    private void cbxEstado(ActionEvent event) {
        switch (cbxEstado.getValue()) {
            case "Emergente":
                this.lblID.setText("1");
                break;
            case "Normal":
                this.lblID.setText("2");
        }
    }

}
