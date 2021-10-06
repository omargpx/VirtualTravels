/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class EnviosController implements Initializable {

    @FXML
    private ComboBox cbxDestinos;
    @FXML
    private ComboBox cbxPeso;
    @FXML
    private Label txtPeso;
    @FXML
    private Label txtCostos;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtapePaterno;
    @FXML
    private TextField txtapeMaterno;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtDE;
    @FXML
    private TextField txtPara;
    @FXML
    private TextField txtDniReceptor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //mostrando las ciudades escritas en el observalelist en el combobox
        ObservableList<String> dest = FXCollections.observableArrayList("Lima","Cusco","Ica","Tarapoto","Arequipa","Puno","Tumbes",
                "Huascaran","Iquitos","Madre de Dios");
        cbxDestinos.setItems(dest);
        //mostrando las masas escritas en el observalelist en el combobox
        ObservableList<String> peso = FXCollections.observableArrayList("10 kg","15 kg","20 kg","30 kg","40 kg","50 kg","80 kg",
                "90 kg","100 kg","200 kg +");
        cbxPeso.setItems(peso);
    }    
    @FXML
    public void cbxseleccionPeso(ActionEvent event){ // PRECIOS DE ACUERDO AL DESTINO Y EL PESO
        txtPeso.setText((String) cbxPeso.getValue());
        String peso= txtPeso.getText();
        if(peso.equals("10 kg")){
            txtCostos.setText("S/ 19");
        }else if(peso.equals("15 kg")){
            txtCostos.setText("S/ 28.50");
        }else if(peso.equals("20 kg")){
            txtCostos.setText("S/ 38");
        }else if(peso.equals("30 kg")){
            txtCostos.setText("S/ 57");
        }else if(peso.equals("40 kg")){
            txtCostos.setText("S/ 76");
        }else if(peso.equals("50 kg")){
            txtCostos.setText("S/ 85");
        }else if(peso.equals("80 kg")){
            txtCostos.setText("S/ 142");
        }else if(peso.equals("90 kg")){
            txtCostos.setText("S/ 161");
        }else if(peso.equals("100 kg")){
            txtCostos.setText("S/ 180");
        }else if(peso.equals("200 kg +")){
            txtCostos.setText("S/360 - S/500");
        }
    }
    private void executeQuery(String query) {
        Conexion conex = new Conexion();
       
        Connection conn = conex.getConnection();
       
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void insertarEnvios(){// metodo insertar o guardar en la bd 
          
        try {
            String query = "INSERT INTO enviosp VALUES (" 
            + txtNombre.getText() 
            + ",'" + txtapePaterno.getText()
            + "','" + txtapeMaterno.getText() 
            + "','" + txtDni.getText()
            + "','"  + cbxDestinos.getValue()
            + "','" + cbxPeso.getValue()
            + "','" + txtDE.getText()
            + "','" + txtPara.getText()
            + "','" + txtDniReceptor.getText() + "')";
            executeQuery(query);
            //notificacion error al ingresar usuario no registrado
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;        
            tray.setAnimationType(type);
            tray.setTitle("Boleto generado");
            tray.setMessage("Envio realizado");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2900));
        } catch (Exception e) {
            System.out.println("error ->"+ e.getMessage());
            TrayNotification t = new TrayNotification();
            AnimationType type = AnimationType.POPUP;        
            t.setAnimationType(type);
            t.setAnimationType(type);
            t.setTitle("Error al relaizar envio");
            t.setMessage("No guardado");
            t.setNotificationType(NotificationType.WARNING);
            t.showAndDismiss(Duration.millis(2900));
        }
        
    }

    @FXML
    private void btnGenerarfactura(ActionEvent event) {
        insertarEnvios();
    }

}
