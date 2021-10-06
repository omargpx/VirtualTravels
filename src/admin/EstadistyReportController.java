/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class EstadistyReportController implements Initializable {

    @FXML
    private AnchorPane apChange;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane center = FXMLLoader.load(getClass().getResource("/admin/estadistica.fxml"));
            apChange.getChildren().setAll(center);
        } catch (IOException ex) {
            Logger.getLogger(EstadistyReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEstadisticas(ActionEvent event) throws IOException {
        AnchorPane center = FXMLLoader.load(getClass().getResource("/admin/estadistica.fxml"));
        apChange.getChildren().setAll(center);
    }

    @FXML
    private void btnComunicados(ActionEvent event) throws IOException {
        AnchorPane center = FXMLLoader.load(getClass().getResource("/admin/comunicados.fxml"));
        apChange.getChildren().setAll(center);
    }

    @FXML
    private void btnReportes(ActionEvent event) {
    }

    @FXML
    private void btnProblemasReportados(ActionEvent event) {
    }

}
