/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class ScSoporteController implements Initializable {

    @FXML
    private TextField txtmsjReporte;
    @FXML
    private Text msjCliente;
    @FXML
    private ImageView imgCliente;
    @FXML
    private CheckBox btnDarkThem;
    @FXML
    private Pane paneDark1;
    @FXML
    private Pane paneDark2;
    @FXML
    private HBox hbxMSJclient;
    @FXML
    private ImageView imgVT;
    @FXML
    private HBox hbxVT;
    @FXML
    private Text txtRespuestSistem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.imgCliente.setVisible(false);
        hbxMSJclient.setVisible(false);
        this.paneDark1.setVisible(false);
        this.paneDark2.setVisible(false);
        this.hbxVT.setVisible(false);
        imgVT.setVisible(false);
    }

    @FXML
    private void btnDarkThem(ActionEvent event) {
        if (btnDarkThem.isSelected()) {
            this.paneDark1.setVisible(true);
            this.paneDark2.setVisible(true);
        } else {
            this.paneDark1.setVisible(false);
            this.paneDark2.setVisible(false);
        }
    }

    @FXML
    private void txtKeypresed_reportProblem(KeyEvent event) {
        if (msjCliente.getText().equals("")) {
            imgCliente.setVisible(true);
            hbxMSJclient.setVisible(true);
            this.msjCliente.setText(". . .");
            this.msjCliente.setStyle("-fx-text-alignment: right;");
        }
    }

    @FXML
    private void btnEnviar(ActionEvent event) {
        String Cap = txtmsjReporte.getText();
        if(Cap.equals("")){
            JOptionPane.showMessageDialog(null, "no se escribio nada");
        }else if(imgCliente.isVisible()){
            this.hbxVT.setVisible(true);
            imgVT.setVisible(true);
            this.msjCliente.setText(Cap);
            this.msjCliente.setStyle("-fx-text-alignment: left;");
            this.txtRespuestSistem.setText("Gracias por su consulta, en un momento sera revisada y atendida");
        }
    }

}
