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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import virtualtravels.model.Cliente;
import virtualtravels.model.Usuarios;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class UserClienteController implements Initializable, Icrud {

    @FXML
    private TextField txtEmail;
    @FXML
    private ToggleGroup groupSex;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtApePaternoCliente;
    @FXML
    private TextField txtApeMaternoCliente;
    @FXML
    private TextField txtTelefonoCliente;
    @FXML
    private RadioButton rdMasculinoCliente;
    @FXML
    private RadioButton rdFemeninoCliente;
    @FXML
    private TextField txtDniCliente;
    @FXML
    private TextField txtIdCliente;
    // variable publica utilizada en radiobtn del grupo de genero
    public String sexo = "U";
    @FXML
    private TableColumn<Cliente, Integer> colIdCliente;
    @FXML
    private TableColumn<Cliente, String> colNombreCliente;
    @FXML
    private TableColumn<Cliente, String> colApePaternoCliente;
    @FXML
    private TableColumn<Cliente, String> colApeMaternoCliente;
    @FXML
    private TableColumn<Cliente, String> coltelefonoCliente;
    @FXML
    private TableColumn<Cliente, String> colDniCliente;
    @FXML
    private TableView tablaCliente;
    @FXML
    private TableColumn<Cliente, String> colSexoCliente;
    @FXML
    private TableColumn<Cliente, String> colEmailCliente;
    @FXML
    private Pane paneAgregar;
    @FXML
    private Label lblTituloIcrud;
    @FXML
    private Button btnActualizarCliente;
    @FXML
    private Button btnGuardarCliente;
    @FXML
    private HBox hbxBtnICRUD;
    @FXML
    private Pane paneWarninRemove;
    @FXML
    private HBox cbxTitleWindow;
    @FXML
    private Pane paneInfoUsu;
    @FXML
    private Label lblNombreUsu;
    @FXML
    private Label lblNombresUsus;
    @FXML
    private Label lblApellidosUsus;
    @FXML
    private Label lblViaje_one;
    @FXML
    private Label lbldate_one;
    @FXML
    private Label lblTelephone;
    @FXML
    private Label lblDNI;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDatos();
        this.paneAgregar.setVisible(false);
        this.btnActualizarCliente.setVisible(false);
        this.btnGuardarCliente.setVisible(false);
        paneWarninRemove.setVisible(false);
        paneInfoUsu.setVisible(false);
    }

    @Override
    public void insertar() {
        // obteniendo  lo  valores del  formulario del  radio  button
        if (rdMasculinoCliente.isSelected()) {
            sexo = "M";
        } else if (rdFemeninoCliente.isSelected()) {
            sexo = "F";
        }

        String nombreCliente = txtNombreCliente.getText();
        String ApePaternoCliente = txtApePaternoCliente.getText();
        String ApeMaternoCliente = txtApeMaternoCliente.getText();
        String telefonoCliente = txtTelefonoCliente.getText();
        String emailCliente = txtEmail.getText();
        String dniCliente = txtDniCliente.getText();

        //insertando en la tabla de la bd
        String query = "INSERT INTO userclientes(nombreCliente,ApePaternoCliente,ApeMaternoCliente,telefono,email,sexo,dni) VALUES ("
                +"'" + nombreCliente
                + "','" + ApePaternoCliente
                + "','" + ApeMaternoCliente
                + "','" + telefonoCliente
                + "','" + emailCliente
                + "','" + sexo
                + "','" + dniCliente + "');";

        executeQuery(query);
        //Notificación
        String nombreUSU = txtNombreCliente.getText() + " " + txtApePaternoCliente.getText() + " GUARDADO";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        //capurando msj
        tray.setAnimationType(type);
        tray.setTitle("GUARDADO CON EXITO");
        tray.setMessage(nombreUSU);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
        //fin notificacion
        mostrarDatos();
    }
// implementacion  del  interface

    @Override
    public void actualizar() {
        if (rdMasculinoCliente.isSelected()) {
            sexo = "M";
        } else if (rdFemeninoCliente.isSelected()) {
            sexo = "F";
        }
        String query = "UPDATE userclientes SET nombreCliente ='" + txtNombreCliente.getText()
                + "',ApePaternoCliente='" + txtApePaternoCliente.getText() + "',ApeMaternoCliente='"
                + txtApeMaternoCliente.getText() + "',telefono='" + txtTelefonoCliente.getText() + "',email='" + txtEmail.getText() + "',sexo='" + sexo
                + "',dni='" + txtDniCliente.getText()
                + "'where idCliente ='" + txtIdCliente.getText() + "'";
        //Notificación
        String correo = "Actualizado";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        //capurando msj
        tray.setAnimationType(type);
        tray.setTitle("Usuario " + txtNombreCliente.getText() + " Actualizado");
        tray.setMessage(correo);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
        //fin notificacion
        executeQuery(query);
        mostrarDatos();
    }

    @Override
    public void eliminar() {
        String query = "DELETE FROM userclientes WHERE idCliente ='" + txtIdCliente.getText() + "'";
        executeQuery(query);
        mostrarDatos();
    }

    @Override
    public ObservableList<virtualtravels.model.Cliente> obtenerCliente() {

        ObservableList<virtualtravels.model.Cliente> clienteList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM userclientes";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.Cliente clientes;
            while (rs.next()) {
                clientes = new virtualtravels.model.Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombreCliente"),
                        rs.getString("ApePaternoCliente"),
                        rs.getString("ApeMaternoCliente"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("sexo"),
                        rs.getString("dni"));
                clienteList.add(clientes);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clienteList;
    }

    @Override
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

    @Override
    public void mostrarDatos() {

        ObservableList<virtualtravels.model.Cliente> list = obtenerCliente();

        colIdCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, Integer>("idCliente"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, String>("nombreCliente"));
        colApePaternoCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, String>("ApePaternoCliente"));
        colApeMaternoCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, String>("ApeMaternoCliente"));
        coltelefonoCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, String>("telefono"));
        colEmailCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, String>("email"));
        colSexoCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, String>("sexo"));
        colDniCliente.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Cliente, String>("dni"));

        tablaCliente.setItems(list);
    }

    @Override
    public void seleccionColumna() {

        int index = tablaCliente.getSelectionModel().getFocusedIndex(); // capturo el indice 

        if (index <= -1) {
            JOptionPane.showMessageDialog(null, "usted no seleciono un elemento valido ");
        }
        txtIdCliente.setText(colIdCliente.getCellData(index).toString());
        txtNombreCliente.setText(colNombreCliente.getCellData(index).toString());
        txtApePaternoCliente.setText(colApePaternoCliente.getCellData(index).toString());
        txtApeMaternoCliente.setText(colApeMaternoCliente.getCellData(index).toString());
        txtTelefonoCliente.setText(coltelefonoCliente.getCellData(index).toString());
        txtDniCliente.setText(colDniCliente.getCellData(index).toString());
        txtEmail.setText(colEmailCliente.getCellData(index).toString());
        //panel de Informacion
        lblNombreUsu.setText(colNombreCliente.getCellData(index).toString() + " " + colApePaternoCliente.getCellData(index).toString());
        lblNombresUsus.setText(colNombreCliente.getCellData(index).toString());
        lblApellidosUsus.setText(colApePaternoCliente.getCellData(index).toString() + " " + colApeMaternoCliente.getCellData(index).toString());
        lblCorreo.setText(colEmailCliente.getCellData(index).toString());
        lblTelephone.setText(coltelefonoCliente.getCellData(index).toString());
        lblDNI.setText(colDniCliente.getCellData(index).toString());
        String genCliente = colSexoCliente.getCellData(index).toString();
        if (genCliente.equals("M")) {
            lblGenero.setText("Masculino");
        } else if (genCliente.equals("M")) {
            lblGenero.setText("Femenino");
        }

    }

    @FXML
    private void btnGuardarCliente(ActionEvent event) {
        insertar();
        this.tablaCliente.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
        this.cbxTitleWindow.setDisable(false);
        this.paneAgregar.setVisible(false);
    }

    @FXML
    private void btnActualizarCliente(ActionEvent event) {

        actualizar();
        this.tablaCliente.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
        this.cbxTitleWindow.setDisable(false);
        this.paneAgregar.setVisible(false);
    }

    @FXML
    private void btnEliminarCliente(ActionEvent event) {
        String cap = txtIdCliente.getText();
        if (cap.equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccionar Usuario");
        } else {
            paneWarninRemove.setVisible(true);
            this.tablaCliente.setDisable(true);
            this.hbxBtnICRUD.setDisable(true);
            this.cbxTitleWindow.setDisable(true);
        }
    }

    @Override
    public ObservableList<Usuarios> obtenerUsuario() {
        return null;
    }

    @FXML
    private void seleccionColumnaCliente(MouseEvent event) {
        seleccionColumna();
    }

    @FXML
    private void btnAgregar(ActionEvent event) {//agregar nuevo usuario vista
        this.tablaCliente.setDisable(true);
        this.cbxTitleWindow.setDisable(true);
        this.paneAgregar.setVisible(true);
        this.lblTituloIcrud.setText("Nuevo Usuario");
        this.btnGuardarCliente.setVisible(true);
        this.btnActualizarCliente.setVisible(false);
        this.hbxBtnICRUD.setDisable(true);
        //limpiando casillas
        txtIdCliente.setText("");
        txtNombreCliente.setText("");
        txtApePaternoCliente.setText("");
        txtApeMaternoCliente.setText("");
        txtDniCliente.setText("");
        txtTelefonoCliente.setText("");
        txtEmail.setText("");
    }

    @FXML
    private void btnClosePneAgregar(ActionEvent event) { //rehabilitar
        this.paneAgregar.setVisible(false);
        this.tablaCliente.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
        this.cbxTitleWindow.setDisable(false);
    }

    @FXML
    private void btnActualizarPnel(ActionEvent event) {//mostrar `panel actualizar
        String cap = txtIdCliente.getText();
        if (cap.equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccionar Usuario");
        } else {
            this.paneAgregar.setVisible(true);
            this.tablaCliente.setDisable(true);
            this.cbxTitleWindow.setDisable(true);
            this.lblTituloIcrud.setText("Actualizar Datos");
            this.btnActualizarCliente.setVisible(true);
            this.btnGuardarCliente.setVisible(false);
            this.hbxBtnICRUD.setDisable(true);
        }
    }

    @FXML
    private void btnRemoveUser_paneWarning(ActionEvent event) {
        String query = "DELETE FROM userclientes WHERE idCliente ='" + txtIdCliente.getText() + "'";
        executeQuery(query);
        this.paneWarninRemove.setVisible(false);
        this.hbxBtnICRUD.setDisable(false);
        this.tablaCliente.setDisable(false);
        this.cbxTitleWindow.setDisable(false);
        mostrarDatos();

    }

    @FXML
    private void btnCancelRemove(ActionEvent event) {
        this.paneWarninRemove.setVisible(false);
        this.hbxBtnICRUD.setDisable(false);
        this.tablaCliente.setDisable(false);
        this.cbxTitleWindow.setDisable(false);
    }

    @FXML
    private void btnInfoUsu(ActionEvent event) {
        String p = lblNombreUsu.getText();
        if (p.equals("Nombre de Usuario")) {
            JOptionPane.showMessageDialog(null, "Seleccione un Usuario");
        } else {
            paneInfoUsu.setVisible(true);
            tablaCliente.setDisable(true);
            hbxBtnICRUD.setDisable(true);
        }
    }

    @FXML
    private void btnCloseInfo(ActionEvent event) {
        paneInfoUsu.setVisible(false);
        tablaCliente.setDisable(false);
        hbxBtnICRUD.setDisable(false);
    }
}
