package login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import virtualtravels.Conexion;
import virtualtravels.HomeController;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField txtCorreoAdm;
    @FXML
    private HBox hbxTOP;
    @FXML
    private CheckBox chckContrasena;
    @FXML
    private Pane paneRegistro;
    @FXML
    private Pane paneButons;
    @FXML
    private AnchorPane ap;
    @FXML
    private VBox paneInicioLog;
    @FXML
    private TextField txtUsuarioLogin;
    @FXML
    private PasswordField txtContrasenaLog;
    @FXML
    private RadioButton rdAdministrador;
    @FXML
    private ToggleGroup oficio;
    @FXML
    private RadioButton rdTrabajador;
    //atributos para mover
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Pane paneShowMSJ;
    @FXML
    private Label txtTitleShowMSJ;
    @FXML
    private Label txtMsjShow;
    @FXML
    private PasswordField pswrdContrasenaAdm;
    @FXML
    private TextField txtShowContrasenaAdm;
    @FXML
    private Pane paneAgregar;
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
    private Button btnGuardar;
    @FXML
    private Pane paneOpacity;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.paneShowMSJ.setVisible(false);
        this.txtShowContrasenaAdm.setVisible(false);
        paneAgregar.setVisible(false);
        paneOpacity.setVisible(false);
    }

    @FXML
    private void chckContrasena(ActionEvent event) {
        if (chckContrasena.isSelected()) {
            String cap = pswrdContrasenaAdm.getText();
            this.txtShowContrasenaAdm.setText(cap);
            this.txtShowContrasenaAdm.setVisible(true);
        } else {
            this.txtShowContrasenaAdm.setVisible(false);
            String cap = txtShowContrasenaAdm.getText();
            this.pswrdContrasenaAdm.setText(cap);
        }
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1.1), paneButons);
        t.setToX(ap.getLayoutX());
        t.play();
        TranslateTransition tr = new TranslateTransition(Duration.seconds(1), paneRegistro);
        tr.setToX(ap.getLayoutX());
        tr.play();
    }

    @FXML
    private void btnIniciarSesion(ActionEvent event) throws IOException {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), paneButons);
        t.setToX(paneButons.getLayoutX() + (ap.getPrefWidth() - paneButons.getPrefWidth()) + 1);
        t.play();
        TranslateTransition tr = new TranslateTransition(Duration.seconds(2.1), paneRegistro);
        tr.setToX(595);
        tr.play();
    }

    @FXML
    private void btnMinimizar(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage ventanaACt = (Stage) source.getScene().getWindow();
        ventanaACt.setIconified(true);
    }

    @FXML
    private void btnExit(MouseEvent event) {
        System.exit(0);
    }

    public ObservableList<virtualtravels.model.Usuarios> ObtenerUsuarios() {//Obteniendo los usuarios de la BD
        ObservableList<virtualtravels.model.Usuarios> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM usuarios where usuarios = '" + txtUsuarioLogin.getText() + "'";
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

    public ObservableList<virtualtravels.model.adminVT> ObtenerUsuarioAdm() {//Obteniendo los usuarios de la BD
        ObservableList<virtualtravels.model.adminVT> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM adminVT where usuarioAdm = '" + txtUsuarioLogin.getText() + "'";
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

    public ObservableList<virtualtravels.model.adminVT> ObtenerClaveEmpresa() {//Obteniendo claveeMPRESA
        ObservableList<virtualtravels.model.adminVT> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM adminVT where id = '1'";
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

    @FXML
    private void btnIngresar(MouseEvent event) {
        ObservableList<virtualtravels.model.Usuarios> usu = ObtenerUsuarios();
        ObservableList<virtualtravels.model.adminVT> adm = ObtenerUsuarioAdm();
        String txtUsu, txtPaswrd;
        txtUsu = txtUsuarioLogin.getText();
        txtPaswrd = txtContrasenaLog.getText();
        if (txtUsu.equals("") || txtPaswrd.equals("")) {
            ShowMessage("MENSAJE", "LLenar casillas");
        } else {
            if (rdAdministrador.isSelected()) {
                if (adm.isEmpty()) {
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    tray.setAnimationType(type);
                    tray.setTitle("Error al iniciar Sesión");
                    tray.setMessage("ADMINISTRADOR " + txtUsuarioLogin.getText() + " NO REGISTRADO");
                    tray.setNotificationType(NotificationType.WARNING);
                    tray.showAndDismiss(Duration.millis(2900));
                } else {
                    adm.forEach((admVt) -> {
                        String clave = admVt.getContrasena();
                        if (txtPaswrd.equals(clave)) {
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("/admin/HomeAdm.fxml"));
                                Stage stage = new Stage();
                                stage.initStyle(StageStyle.UNDECORATED);
                                Scene scene = new Scene(root);
                                Image ico = new Image("iconos/logVT.png");
                                stage.getIcons().add(ico);
                                //movimiento de ventana
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
                                stage.setScene(scene);
                                stage.show();
                                //cerrar ventana login
                                Node source = (Node) event.getSource();
                                Stage ventanaACt = (Stage) source.getScene().getWindow();
                                ventanaACt.close();
                            } catch (IOException ex) {
                                System.out.println("error -> gaaaaa");;
                            }
                        } else {
                            TrayNotification tray = new TrayNotification();
                            AnimationType type = AnimationType.POPUP;
                            tray.setAnimationType(type);
                            tray.setTitle("CONTRASEÑA INCORECTA");
                            tray.setMessage("Error al iniciar Sesión");
                            tray.setNotificationType(NotificationType.WARNING);
                            tray.showAndDismiss(Duration.millis(2900));
                        }
                    });
                }
            } else if (rdTrabajador.isSelected()) {
                if (usu.isEmpty()) {
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    tray.setAnimationType(type);
                    tray.setTitle("Error al iniciar Sesión");
                    tray.setMessage("TRABAJADOR " + txtUsuarioLogin.getText() + " NO REGISTRADO");
                    tray.setNotificationType(NotificationType.WARNING);
                    tray.showAndDismiss(Duration.millis(2900));
                } else {
                    usu.forEach((item) -> {
                        String contra = item.getContrasena();
                        if (txtPaswrd.equals(contra)) {
                            String nombreUsu = item.getNombre() + " " + item.getApePaterno();
                            String Correo = item.getUsuario();
                            String nombreUsuario = item.getNombre() + " " + item.getApePaterno() + " " + item.getApeMaterno();
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/virtualtravels/Home.fxml"));
                                Parent root = loader.load();
                                //Notificación -__-
                                TrayNotification tray = new TrayNotification();
                                AnimationType type = AnimationType.POPUP;
                                tray.setAnimationType(type);
                                tray.setTitle("BIENVENIDO " + item.getNombre() + " " + item.getApePaterno());
                                tray.setMessage("" + item.getUsuario());
                                tray.setNotificationType(NotificationType.SUCCESS);
                                tray.showAndDismiss(Duration.millis(3000));
                                //fin notificacion -___-

                                /*MOVIMIENTO DE LA VENTANA*/
                                Stage stage = new Stage();
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
                                //capturando controlador
                                HomeController controlador = loader.getController();
                                controlador.nombreUsu(nombreUsu, nombreUsuario, Correo);
                                Scene scene = new Scene(root);

                                stage.setTitle("Virtual Travels");
                                Image ico = new Image("/iconos/logVT.png");
                                stage.getIcons().add(ico);
                                stage.resizableProperty().setValue(Boolean.FALSE); // deshabilitar maximizar
                                stage.setScene(scene);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();
                                //cerrar inicio
                                Node source = (Node) event.getSource();
                                Stage ventanaACt = (Stage) source.getScene().getWindow();
                                ventanaACt.close();
                            } catch (Exception e) {
                                System.out.println("error ->" + e.getMessage());
                            }
                        } else {
                            TrayNotification tray = new TrayNotification();
                            AnimationType type = AnimationType.POPUP;
                            tray.setAnimationType(type);
                            tray.setTitle("CONTRASEÑA INCORECTA");
                            tray.setMessage("Error al iniciar Sesión");
                            tray.setNotificationType(NotificationType.WARNING);
                            tray.showAndDismiss(Duration.millis(2900));
                        }
                    });
                }
            } else {
                //JOptionPane.showMessageDialog(null,"SELECCIONAR UN OFICIO");
                ShowMessage("WARNING", "Seleccionar un Oficcio");
            }
        }
    }

    @FXML
    private void btnConfiGeneral(ActionEvent event) throws IOException {
        AnchorPane config = FXMLLoader.load(getClass().getResource("/soporte/ConfigGeneral.fxml"));
        ap.getChildren().setAll(config);
    }

    private void ShowMessage(String title, String message) {
        //desabilitando
        this.paneInicioLog.setDisable(true);
        this.hbxTOP.setDisable(true);
        this.paneButons.setDisable(true);
        ap.setOpacity(0.7);
        //mostrar mensaje
        this.paneShowMSJ.setVisible(true);
        this.txtTitleShowMSJ.setText(title);
        txtTitleShowMSJ.setStyle("-fx-text-fill: black;");
        this.txtMsjShow.setText(message);
        txtMsjShow.setStyle("-fx-text-fill: black;");
    }

    @FXML
    private void btnCloseShowMSJ(ActionEvent event) {
        this.paneShowMSJ.setVisible(false);
        this.paneInicioLog.setDisable(false);
        this.hbxTOP.setDisable(false);
        this.paneButons.setDisable(false);
        ap.setOpacity(1);
    }

    @FXML
    private void btnAceptarShowMSJ(ActionEvent event) {
        //rehabilitando
        ap.setOpacity(1);
        this.paneShowMSJ.setVisible(false);
        this.paneInicioLog.setDisable(false);
        this.hbxTOP.setDisable(false);
        this.paneButons.setDisable(false);

    }

    @FXML
    private void btnContinuarAdm(ActionEvent event) {
        ObservableList<virtualtravels.model.adminVT> adm = ObtenerUsuarioAdm();
        String casillaUno, CasillaDos;
        casillaUno = txtCorreoAdm.getText();
        CasillaDos = pswrdContrasenaAdm.getText();
        if (casillaUno.equals("") || CasillaDos.equals("")) {
            JOptionPane.showMessageDialog(null, "Llenar Casillas");
        } else {
            adm.forEach((pas) -> {
                //datos reales de la base de datos
                String correo = pas.getUsuarioAdm();
                String contrasena = pas.getContrasena();
                if (casillaUno.equals(correo)) {
                    System.out.println("correo correcto");
                    if (CasillaDos.equals(contrasena)) {
                        System.out.println("contraseña correcta");
                    } else {
                        System.out.println("contraseña incorrecta");
                    }
                } else {
                    System.out.println("correo incorrecto");
                }
            });
        }
    }

    @FXML
    private void btnClosePneAgregar(ActionEvent event) {
        this.paneAgregar.setVisible(false);
        paneOpacity.setVisible(false);
    }

    @FXML
    private void btnGuardar(ActionEvent event) throws IOException {
        //casillas
        String nombre, ApP, ApM, user, psw;
        nombre = txtNombre.getText();
        ApP = txtApePaterno.getText();
        ApM = txtApeMaterno.getText();
        user = txtUsuario.getText();
        psw = txtContrasena.getText();
        if (nombre.equals("") || ApP.equals("") || ApM.equals("") || user.equals("") || psw.equals("")) {
            JOptionPane.showMessageDialog(null, "llenar casilas");
        } else {
            String query = "INSERT INTO usuarios(nombre,ApePaterno,ApeMaterno,usuarios,contrasena) VALUES ("
                    + "'" + txtNombre.getText()
                    + "','" + txtApePaterno.getText()
                    + "','" + txtApeMaterno.getText()
                    + "','" + txtUsuario.getText()
                    + "','" + txtContrasena.getText() + "');";

            executeQuery(query);
            //notificacion de usuario registrado a partir de libreria traynotification.jar
            String correo = "Guardado";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            //capurando msj
            tray.setAnimationType(type);
            tray.setTitle("Usuario " + txtNombre.getText() + " Registrado");
            tray.setMessage(correo);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            //siguiente vemtana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/bienbenidaUser.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
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
            //controler
            BienbenidaUserController controler = loader.getController();
            controler.parametros(nombre, ApP, ApM, user);
            Scene scene = new Scene(root);
            Image ico = new Image("/iconos/logVT.png");
            stage.getIcons().add(ico); // deshabilitar maximizar
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //cerrar inicio
            Node source = (Node) event.getSource();
            Stage ventanaACt = (Stage) source.getScene().getWindow();
            ventanaACt.close();
        }
    }

    private void executeQuery(String query) {
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
    private void linkCuentaCorriente(ActionEvent event) {
        this.paneAgregar.setVisible(true);
        paneOpacity.setVisible(true);
    }

}
