/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import virtualtravels.model.Producto;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class LugaresController implements Initializable {

    @FXML
    private TableView tablaLugares;
    @FXML
    private TableColumn<Producto, Integer> colID;
    @FXML
    private TableColumn<Producto, String> colLugares;
    @FXML
    private TableColumn<Producto, String> colPasajes;
    @FXML
    private TextField txtID;
    @FXML
    private ComboBox cbxLugares;
    @FXML
    private TextField txtPasajes;
    @FXML
    private HBox hbxBtnICRUD;
    @FXML
    private Label lblTitlePane;
    @FXML
    private Pane paneICRUDproductos;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Mostrando lugares disponiebles en el comboBox
        ObservableList<String> lugares = FXCollections.observableArrayList("Lima", "Cusco", "Ica", "Tarapoto", "Arequipa", "Puno", "Tumbes",
                "Huascaran", "Iquitos", "Madre de Dios");
        cbxLugares.setItems(lugares);
        paneICRUDproductos.setVisible(false);
        mostrar();
    }

    public void mostrar() {
        ObservableList<virtualtravels.model.Producto> list = ObtenerUsuarios();

        colID.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Producto, Integer>("idProducto"));
        colLugares.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Producto, String>("lugar"));
        colPasajes.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Producto, String>("pasajes"));

        tablaLugares.setItems(list);
    }

    public ObservableList<virtualtravels.model.Producto> ObtenerUsuarios() {//Obteniendo los usuarios de la BD
        ObservableList<virtualtravels.model.Producto> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM producto";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.Producto producto;
            while (rs.next()) {
                producto = new virtualtravels.model.Producto(rs.getInt("idProducto"),
                        rs.getString("lugar"),
                        rs.getString("pasajes"));
                usuarioList.add(producto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuarioList;
    }

    @FXML
    private void btnGuardar(ActionEvent event) {//Gurdando en la base de Datos (BD)
        String idProducto = txtID.getText();
        String numPasaje = txtPasajes.getText();

        String query = "INSERT INTO producto VALUES ("
                + idProducto
                + ",'" + cbxLugares.getValue()
                + "','" + numPasaje + "')";

        executeQuery(query);
        JOptionPane.showMessageDialog(null, "Guardado con exito");
        mostrar();
        paneICRUDproductos.setVisible(false);
        this.tablaLugares.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        String query = "UPDATE producto SET idProducto ='" + txtID.getText() + "',lugar='" + cbxLugares.getValue()
                + "',pasajes='" + txtPasajes.getText() + "'where idProducto ='" + txtID.getText() + "'";

        executeQuery(query);
        mostrar();
        paneICRUDproductos.setVisible(false);
        this.tablaLugares.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
    }

    @FXML
    private void seleccionColumna(MouseEvent event) {
        int index = tablaLugares.getSelectionModel().getFocusedIndex(); // capturo el indice 

        if (index <= -1) {
            JOptionPane.showMessageDialog(null, "usted no seleciono un elemento valido ");
        }
        txtID.setText(colID.getCellData(index).toString());
        txtPasajes.setText(colPasajes.getCellData(index).toString());
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
    private void btnAgregarPanel(ActionEvent event) {
        txtID.setText("");
        txtPasajes.setText("");
        paneICRUDproductos.setVisible(true);
        this.lblTitlePane.setText("Agregar Nuevo Destino");
        this.tablaLugares.setDisable(true);
        this.btnActualizar.setVisible(false);
            this.btnGuardar.setVisible(true);
        this.hbxBtnICRUD.setDisable(true);
    }

    @FXML
    private void btnActualizarPnel(ActionEvent event) {
        String cap = txtID.getText();
        if (cap.equals("")) {
            JOptionPane.showMessageDialog(null, "seleccione un destino");
        } else {
            paneICRUDproductos.setVisible(true);
            this.btnActualizar.setVisible(true);
            this.btnGuardar.setVisible(false);
            this.lblTitlePane.setText("Actualizar Destino");
            this.tablaLugares.setDisable(true);
            this.hbxBtnICRUD.setDisable(true);
        }
    }

    @FXML
    private void btnInfo(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "estamos trabajando en ello");
    }

    @FXML
    private void btnClose(ActionEvent event) {
        paneICRUDproductos.setVisible(false);
        this.tablaLugares.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
    }

}
