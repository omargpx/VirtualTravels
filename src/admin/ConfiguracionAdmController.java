/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import virtualtravels.Conexion;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class ConfiguracionAdmController implements Initializable {

    @FXML
    private CheckBox checkConfirmacionCuenta;
    @FXML
    private Pane paneCambioCuenta;
    @FXML
    private ToggleButton btnChangeCuenta;
    @FXML
    private Button btnGuardar_panelChangeCuenta;
    @FXML
    private TextField txtUsuarioAdm;
    @FXML
    private PasswordField txtClaveAdmNew;
    @FXML
    private PasswordField txtClaveAdmAntig;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //panel CAMBIAR CUENTA ADMIN
        paneCambioCuenta.setVisible(false);
        btnGuardar_panelChangeCuenta.setDisable(true);

        //end changes
    }

    @FXML
    private void btnChangeCuenta(ActionEvent event) {
        if (btnChangeCuenta.isSelected()) {
            if (checkConfirmacionCuenta.isSelected()) {
                this.btnGuardar_panelChangeCuenta.setStyle("-fx-background-color:white; -fx-text-fill:black;");
                this.btnGuardar_panelChangeCuenta.setText("GUARDAR");
                btnGuardar_panelChangeCuenta.setDisable(false);
            } else {
                this.btnGuardar_panelChangeCuenta.setStyle("-fx-background-color:gray; -fx-text-fill:lightgray;");
                this.btnGuardar_panelChangeCuenta.setText("BLOCK");
                btnGuardar_panelChangeCuenta.setDisable(true);
            }
            this.btnChangeCuenta.setStyle("-fx-background-color: gray; -fx-text-fill:white;");
            paneCambioCuenta.setVisible(true);
        } else {
            this.btnChangeCuenta.setStyle("-fx-background-color: white; -fx-text-fill:black;");
            paneCambioCuenta.setVisible(false);
        }
    }

    @FXML
    private void btnGuardarCambiodeCuemta(ActionEvent event) {
        ObservableList<virtualtravels.model.adminVT> clave = ObtenerUsuarioAdm();
        String txtNuevaCuenta, txtClaveNueva;
        txtNuevaCuenta = txtUsuarioAdm.getText();
        txtClaveNueva = txtClaveAdmNew.getText();
        if (txtClaveNueva.equals("") || txtNuevaCuenta.equals("")) {
            JOptionPane.showMessageDialog(null, "Llenar casillas");
        } else {
            if (clave.isEmpty()) {
                JOptionPane.showMessageDialog(null, "CLAVE ANTIGUA incorrecta");
            } else {
                String query = "UPDATE adminVT SET id='1',"+"usuarioAdm = '"+txtNuevaCuenta+"',"+"contrasena = '"+txtClaveNueva+"' "
                        +"WHERE contrasena='"+txtClaveAdmAntig.getText()+"';";
                executeQuery(query);
                System.out.println("Datos cambiados exitosamente"+"\n"+
                        "nueva cuenta : "+txtNuevaCuenta+" Nueva clave : "+txtClaveNueva);
            }
        }

    }

    @FXML
    private void chekCuenta(ActionEvent event) {
        if (checkConfirmacionCuenta.isSelected()) {
            this.btnGuardar_panelChangeCuenta.setStyle("-fx-background-color:white; -fx-text-fill:black;");
            this.btnGuardar_panelChangeCuenta.setText("GUARDAR");
            btnGuardar_panelChangeCuenta.setDisable(false);
        } else {
            this.btnGuardar_panelChangeCuenta.setStyle("-fx-background-color:gray; -fx-text-fill:lightgray;");
            this.btnGuardar_panelChangeCuenta.setText("BLOCK");
            btnGuardar_panelChangeCuenta.setDisable(true);
        }
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

    public ObservableList<virtualtravels.model.adminVT> ObtenerUsuarioAdm() {//Obteniendo los usuarios de la BD
        ObservableList<virtualtravels.model.adminVT> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM adminVT where contrasena = '" + txtClaveAdmAntig.getText() + "'";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.adminVT admin;
            while (rs.next()) {
                admin = new virtualtravels.model.adminVT(rs.getInt("id"),
                        rs.getString("usuarioAdm"),
                        rs.getString("contrasena"),
                        rs.getString("claveEmpresa"));
                usuarioList.add(admin);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuarioList;
    }
}
