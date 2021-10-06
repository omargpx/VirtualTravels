package soporte;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
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
public class ConfiguracionUsuarioController implements Initializable {

    @FXML
    private Label lblHolaUsuario;
    @FXML
    private Label lblCorreo;
    @FXML
    private ToggleGroup configUsuario;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ToggleButton btnNombres;
    @FXML
    private ToggleButton btnContrasena;
    @FXML
    private ToggleButton btnCorreo;
    @FXML
    private Pane paneNombre;
    @FXML
    private TextField txtNewNombre;
    @FXML
    private AnchorPane apconfigUsu;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblNombreUsu_Nombre;
    @FXML
    private TextField txtApellidoPaterno;
    @FXML
    private TextField txtApellidoMaterno;
    @FXML
    private ToggleButton btnPerfil;
    @FXML
    private ImageView img0;
    @FXML
    private Circle circleImg;
    @FXML
    private AnchorPane apPerfil;
    @FXML
    private Label lblhoverInfo;
    private double xOffset = 0;
    private double yOffset = 0;

    public void paramtrsBienvenida(String nombreUsuario, String correo, Paint perfil) throws MalformedURLException {
        this.lblCorreo.setText(correo);
        this.lblHolaUsuario.setText("¡Hola!, " + nombreUsuario);
        lblNombreUsu_Nombre.setText(nombreUsuario);
        this.circleImg.setFill(perfil);
    }

    public static String fechaActual() { // CAPTURA DE LA FECHA ACTUAL
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblFecha.setText(fechaActual());
        paneNombre.setVisible(false);
    }

    @FXML
    private void btnSoporte(ActionEvent event) throws IOException {
        AnchorPane paneUsu = FXMLLoader.load(getClass().getResource("/soporte/ScSoporte.fxml"));
        apconfigUsu.getChildren().setAll(paneUsu);
    }

    @FXML
    private void btnNombres(ActionEvent event) {
        preguntar();
    }

    private void btnApellidos(ActionEvent event) {
        preguntar();
    }

    @FXML
    private void btnContrasena(ActionEvent event) {
        preguntar();
    }

    @FXML
    private void btnCorreo(ActionEvent event) {
        preguntar();
    }

    @FXML
    private void btnPerfil(ActionEvent event) {
        preguntar();
    }

    public void preguntar() {
        if (btnPerfil.isSelected()) {
            img0.setVisible(true);
            img1.setVisible(false);
            img3.setVisible(false);
            img4.setVisible(false);
            //estilo de boton
            this.btnPerfil.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
            this.btnNombres.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnContrasena.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnCorreo.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            //paneles
            this.paneNombre.setVisible(false);
        } else if (btnNombres.isSelected()) {
            img0.setVisible(false);
            img1.setVisible(true);
            img3.setVisible(false);
            img4.setVisible(false);
            //estilo de boton
            this.btnPerfil.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnNombres.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
            this.btnContrasena.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnCorreo.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            //paneles
            this.paneNombre.setVisible(true);
        } else if (btnContrasena.isSelected()) {
            img0.setVisible(false);
            img3.setVisible(true);
            img1.setVisible(false);
            img4.setVisible(false);
            //estilo de boton
            this.btnPerfil.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnContrasena.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
            this.btnNombres.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnCorreo.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            //paneles
        } else if (btnCorreo.isSelected()) {
            img0.setVisible(false);
            img1.setVisible(false);
            img3.setVisible(false);
            img4.setVisible(true);
            //estilo de boton
            this.btnPerfil.setStyle("-fx-background-color:white; -fx-text-fill: black;");
            this.btnCorreo.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
            this.btnNombres.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnContrasena.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            //paneles
        } else {
            img0.setVisible(false);
            img1.setVisible(false);
            img3.setVisible(false);
            img4.setVisible(false);
            //estilo de boton
            this.btnPerfil.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnNombres.setStyle("-fx-background-color:white; -fx-text-fill: black;");
            this.btnContrasena.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            this.btnCorreo.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            //paneles
            paneNombre.setVisible(false);
        }
    }

    @FXML
    private void btnGuardarNombre(ActionEvent event) {
        if (txtNewNombre.getText().equals("") || txtApellidoMaterno.getText().equals("") || txtApellidoPaterno.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLenar Casillas");
        } else {
            String newNombre, newApeP, newApeM;
            newNombre = txtNewNombre.getText();
            newApeP = txtApellidoPaterno.getText();
            newApeM = txtApellidoMaterno.getText();
            String query = "update usuarios set nombre ='" + newNombre + "'," + "ApePaterno ='" + newApeP + "',"
                    + "ApeMaterno ='" + newApeM + "' where usuarios='" + lblCorreo.getText() + "'";
            executeQuery(query);
            //actualizando parametros
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/virtualtravels/Home.fxml"));
                Parent root = loader.load();
                virtualtravels.HomeController controlador = loader.getController();
                controlador.nombreUsu(newNombre + " " + newApeP, newNombre + " " + newApeP + " " + newApeM, lblCorreo.getText());
                //cerrar ventana actual
                Node source = (Node) event.getSource();
                Stage ventanaAct = (Stage) source.getScene().getWindow();
                ventanaAct.close();
                //reabriendo
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Virtual Travels");
                Image ico = new Image("iconos/logVT.png");
                stage.getIcons().add(ico);
                stage.resizableProperty().setValue(Boolean.FALSE); // deshabilitar maximizar
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                //notificacion
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("BIENVENIDO " + newNombre + " " + newApeP);
                tray.setMessage("Datos Actualizados");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                //end notificacion
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ConfiguracionUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ObservableList<virtualtravels.model.Usuarios> ObtenerUsuarios() {//Obteniendo los usuarios de la BD
        ObservableList<virtualtravels.model.Usuarios> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM usuarios where usuarios = '" + lblCorreo.getText() + "'";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.Usuarios usuarios;
            while (rs.next()) {
                usuarios = new virtualtravels.model.Usuarios(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apePaterno"),
                        rs.getString("apeMaterno"),
                        rs.getString("usuarios"),
                        rs.getString("contrasena"));
                usuarioList.add(usuarios);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error ->" + ex.getMessage());
        }
        return usuarioList;
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
    private void linkCambiarPerfil(ActionEvent event) {
        FileChooser im = new FileChooser();
        im.setTitle("Cambiar imagen de perfil");
        Window ownerWindow = null;
        im.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File urls = im.showOpenDialog(ownerWindow);
        if (urls == null) {
            System.out.println("selleccionar imagen");
        } else {
            urls.getAbsoluteFile();
            System.out.println(urls);
            urlImg = urls.toString();
            try {
                Image perfil = new Image(urls.toURL().toString());
                circleImg.setFill(new ImagePattern(perfil));
                /* FXMLLoader home = new FXMLLoader(getClass().getResource("/virtualtravels/Home.fxml"));
                virtualtravels.HomeController ctrl = home.getController();
                ctrl.perfil(urls.toString());*/
            } catch (Exception e) {
                System.out.println("erro -> " + e.getMessage());
            }
        }
    }

    String urlImg = "";

    @FXML
    private void btnGuardarImgPerfil(ActionEvent event) throws IOException {
        ObservableList<virtualtravels.model.Usuarios> usu = ObtenerUsuarios();
        if (urlImg.equals("")) {
               JOptionPane.showMessageDialog(null, "seleccionar una imagen");
        } else {
            usu.forEach((datos) -> {
                try {
                    //actualizando la imagen de perfil principal
                    FXMLLoader home = new FXMLLoader(getClass().getResource("/virtualtravels/Home.fxml"));
                    Parent root = home.load();
                    virtualtravels.HomeController ctrl = home.getController();
                    ctrl.perfil(urlImg);
                    ctrl.nombreUsu(datos.getNombre() + " " + datos.getApePaterno(),
                            datos.getNombre() + " " + datos.getApePaterno() + " " + datos.getApeMaterno(), datos.getUsuario());
                    //reabriendo
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Virtual Travels");
                    Image ico = new Image("iconos/logVT.png");
                    stage.getIcons().add(ico);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        xOffset = event.getSceneX();
                                        yOffset = event.getSceneY();
                                    }
                                });
                                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        stage.setX(event.getScreenX() - xOffset);
                                        stage.setY(event.getScreenY() - yOffset);
                                    }
                                });
                    //--------------
                    Node source = (Node) event.getSource();
                    Stage ventanaAct = (Stage) source.getScene().getWindow();
                    ventanaAct.close();
                } catch (IOException ex) {
                    Logger.getLogger(ConfiguracionUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    @FXML
    private void infoExt(MouseEvent event) {
        this.lblhoverInfo.setVisible(false);
    }

    @FXML
    private void infoEnt(MouseEvent event) {
        this.lblhoverInfo.setVisible(true);
    }

    @FXML
    private void btnEditInfo(MouseEvent event) {
        System.out.println("siiiiu");
    }

}
