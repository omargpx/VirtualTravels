package virtualtravels;

import virtualtravels.model.Usuarios;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
public class EstadisticaController  implements Initializable {

    @FXML
    private TableView tablaUsuario;
    @FXML
    private TableColumn<Usuarios, Integer> colId;
    @FXML
    private TableColumn<Usuarios, String> collNombre;
    @FXML
    private TableColumn<Usuarios, String> colApePaterno;
    @FXML
    private TableColumn<Usuarios, String> colApeMaterno;
    @FXML
    private TableColumn<Usuarios, String> colUsuario;
    @FXML
    private TableColumn<Usuarios, String> colContrasena;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApePaterno;
    @FXML
    private TextField txtApeMaterno;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasena;
    @FXML
    private HBox hbxBtnICRUD;
    @FXML
    private Pane paneAgregar;
    @FXML
    private Label lblTituloIcrud;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Pane paneWarninRemove;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrar();
    }    

    @FXML
    private void seleccionColum(MouseEvent event) {
        int index = tablaUsuario.getSelectionModel().getFocusedIndex(); // capturo el indice 
             
             if(index <= -1){
              JOptionPane.showMessageDialog(null, "usted no seleciono un elemento valido ");
             }
             txtId.setText(colId.getCellData(index).toString());
             txtNombre.setText(collNombre.getCellData(index).toString());
             txtApePaterno.setText(colApePaterno.getCellData(index).toString());
             txtApeMaterno.setText(colApeMaterno.getCellData(index).toString());
             txtUsuario.setText(colUsuario.getCellData(index).toString());
             txtContrasena.setText(colContrasena.getCellData(index).toString());
    }

    @FXML
    private void btnGuardar(ActionEvent event) {//GUARDAR DATOS
        insertarUsuario();//LLAMANDO AL METODO PARA GUARDAR LOS DATOS
        this.paneAgregar.setVisible(false);
        this.tablaUsuario.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
    }
    
    
    private void insertarUsuario(){// metodo insertar o guardar en la bd 
          
        String query = "INSERT INTO usuarios(nombre,ApePaterno,ApeMaterno,usuarios,contrasena) VALUES (" 
            +"'" + txtNombre.getText()
            + "','" + txtApePaterno.getText() 
            + "','" + txtApeMaterno.getText()
            + "','"  + txtUsuario.getText() 
            + "','" + txtContrasena.getText() + "');";
        
        executeQuery(query);
        //notificacion de usuario registrado a partir de libreria traynotification.jar
        String correo = "Guardado";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                             //capurando msj
                    tray.setAnimationType(type);
                    tray.setTitle("Usuario"+txtNombre.getText()+" Guardado");
                    tray.setMessage(correo);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));
        mostrar();
    }
    //mostrando los usuarios en la tabla
    private void mostrar() {//MOSTRAR EN LA TABLA LOS USUARIOS REGISTRADOS
        ObservableList<virtualtravels.model.Usuarios> list = ObtenerUsuarios();
        
        colId.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Usuarios, Integer>("id"));
        collNombre.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Usuarios, String>("nombre"));
        colApePaterno.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Usuarios, String>("ApePaterno"));
        colApeMaterno.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Usuarios, String>("ApeMaterno"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Usuarios, String>("usuario"));
        colContrasena.setCellValueFactory(new PropertyValueFactory<virtualtravels.model.Usuarios, String>("contrasena"));
    
        tablaUsuario.setItems(list);
    
    }
     
 public ObservableList<virtualtravels.model.Usuarios> ObtenerUsuarios(){//Obteniendo los usuarios de la BD
       ObservableList<virtualtravels.model.Usuarios> usuarioList = FXCollections.observableArrayList();
        
       virtualtravels.Conexion conex = new virtualtravels.Conexion();
       
       Connection conn = conex.getConnection();
       
        String query = "SELECT * FROM usuarios";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.Usuarios usuarios;
            while(rs.next()){
                usuarios = new virtualtravels.model.Usuarios(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apePaterno"),
                        rs.getString("apeMaterno"),
                        rs.getString("usuarios"),
                        rs.getString("contrasena"));
                usuarioList.add(usuarios);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return usuarioList;
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

    @FXML
    private void btnActualizar(ActionEvent event) {
       eliminar();
       insertarUsuario();
       this.paneAgregar.setVisible(false);
        this.tablaUsuario.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
    }

    @FXML
    private void btnRemove(ActionEvent event) {
        eliminar();
        this.paneWarninRemove.setVisible(false);
        this.hbxBtnICRUD.setDisable(false);
        this.tablaUsuario.setDisable(false);
    }
    public void eliminar(){//accion eliminar usuario de labase de datos
        String query = "DELETE FROM usuarios WHERE id =" + txtId.getText() + "";
        executeQuery(query);
        mostrar();
    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        this.tablaUsuario.setDisable(true);
        this.paneAgregar.setVisible(true);
        this.lblTituloIcrud.setText("Nuevo Usuario");
        this.btnGuardar.setVisible(true);
        this.btnActualizar.setVisible(false);
        this.hbxBtnICRUD.setDisable(true);
        //limpiando casillas
        txtId.setText("");
        txtNombre.setText("");
        txtApePaterno.setText("");
        txtApeMaterno.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
    }

    @FXML
    private void btnActualizarPnel(ActionEvent event) {
        String cap = txtId.getText();
        if(cap.equals("")){
            JOptionPane.showMessageDialog(null, "Seleccionar Usuario");
        }else{
            this.paneAgregar.setVisible(true);
            this.tablaUsuario.setDisable(true);
            this.lblTituloIcrud.setText("Actualizar Datos");
            this.btnActualizar.setVisible(true);
            this.btnGuardar.setVisible(false);
            this.hbxBtnICRUD.setDisable(true);    
        }
    }

    @FXML
    private void btnInfoUsu(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Hola, tarea no terminada aun");
    }

    @FXML
    private void btnClosePneAgregar(ActionEvent event) {
        this.paneAgregar.setVisible(false);
        this.tablaUsuario.setDisable(false);
        this.hbxBtnICRUD.setDisable(false);
    }

    @FXML
    private void btnEliminarUsuario(ActionEvent event) {
        String cap = txtId.getText();
        if(cap.equals("")){
            JOptionPane.showMessageDialog(null, "Seleccionar Usuario");
        }else{
            this.paneWarninRemove.setVisible(true);
            this.hbxBtnICRUD.setDisable(true);
            this.tablaUsuario.setDisable(true);
        }
    }

    @FXML
    private void btnCancelRemove(ActionEvent event) {
        this.paneWarninRemove.setVisible(false);
        this.hbxBtnICRUD.setDisable(false);
        this.tablaUsuario.setDisable(false);
    }
    
}
