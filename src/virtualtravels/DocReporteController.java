/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class DocReporteController implements Initializable {

    @FXML
    private TextField txtTitleName;
    @FXML
    private TextArea txtContentArea;
    @FXML
    private ComboBox<String> cbxReportes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Scanner files = new Scanner("C:\\Users\\omar\\Documents\\Reportes VirtualTravels");
        ObservableList<String> archivos = FXCollections.observableArrayList(files.toString());
        cbxReportes.setItems(archivos);
    }

    public static String fechaActual() { // CAPTURA DE LA FECHA ACTUAL
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }

    @FXML
    private void btnGuardarDoc(ActionEvent event) {
        File carpeta = new File("C:\\Users\\omar\\Documents\\Reportes_VirtualTravels");
        String nameFile = txtTitleName.getText();
        String contenido = txtContentArea.getText();
        if (nameFile.equals("") || contenido.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("WARNING");
            alert.setContentText("Llenar datos corectamente");
            alert.show();
        } else {
            try {
                if (carpeta.exists()) {
                    System.out.println("* si existe directorio *");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(carpeta + "/" + nameFile + ".doc", true));//creación del doc
                    bw.write(contenido);
                    bw.newLine();
                    bw.write("Virtual travels                                   " + fechaActual());
                    bw.flush();
                    bw.close();
                    JOptionPane.showMessageDialog(null, "Reporte guardado en \n" + "Documentos");
                } else {
                    System.out.println("- - creando carperta - -");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(carpeta + "/" + nameFile + ".doc", true));//creación del doc
                    bw.newLine();
                    bw.write("Virtual travels                                   " + fechaActual());
                    bw.flush();
                    bw.close();
                    JOptionPane.showMessageDialog(null, "Reporte guardado en" + "Documentos");
                }
            } catch (Exception e) {
                System.out.println("eroor ->" + e.getMessage());
            }
        }
    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        FileChooser uw = new FileChooser();
    }

    @FXML
    private void btnUbicacionReporte(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("WARNING");
        alert.setContentText("C:\\Users\\omar\\Documents\\Reportes_VirtualTravels");
        alert.show();
    }

    @FXML
    private void btnAbrirCarpetadeReportes(ActionEvent event) {
        try {//intentado abirir directorio de reportes
            Scanner dir = new Scanner("C:\\Users\\omar\\Documents\\Reportes_VirtualTravels");
            FileChooser windw = new FileChooser();
            windw.setTitle("reportes");
            windw.setInitialFileName(dir.toString());
            Window ownerWindow = null;
            File file = windw.showOpenDialog(ownerWindow);
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop escritorio = java.awt.Desktop.getDesktop();

                if (escritorio.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        java.net.URI uri = new java.net.URI(file.toURI().toString());
                        escritorio.browse(uri);
                        this.txtContentArea.setText(uri.getAuthority());
                    } catch (Exception ex) {
                        System.out.println("error al mostrar archivo" + ex);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("error al mostrar archivo" + ex);
        }
    }

    @FXML
    private void btnNuevoReporte(ActionEvent event) {
        this.txtTitleName.setText("");
        this.txtContentArea.setText("");
    }

    @FXML
    private void btnEliminarReporte(ActionEvent event) {
        String nameFile = txtTitleName.getText();
        if (nameFile.equals("")) {
            JOptionPane.showMessageDialog(null, "ingrese el nombre del archivo");
        } else {
            try {
                File carpeta = new File("C:\\Users\\omar\\Documents\\Reportes_VirtualTravels" + "/" + nameFile);
                carpeta.canExecute();
                carpeta.delete();
                System.out.println(carpeta);
            } catch (Exception e) {
            }
        }
    }
}
