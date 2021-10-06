package login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import virtualtravels.HomeController;

public class LoginInicioController implements Initializable {

    @FXML
    private TextField txtUsuarioLogin;
    @FXML
    private PasswordField txtContrasenaLog;
    @FXML
    private AnchorPane logPane;
    @FXML
    private VBox paneInicioLog;
    @FXML
    private HBox hbxTOP;
    @FXML
    private HBox btnSitioWeb;
    @FXML
    private Pane paneOLVContrasena;
    @FXML
    private TextField txtUsuario_paneOlvCont;
    @FXML
    private PasswordField txtClave_paneOlvContra;
    @FXML
    private ImageView imgContrasenaIncorrecta;
    @FXML
    private Label lblMSJ;
    @FXML
    private AnchorPane apDatosRigth;
    @FXML
    private RadioButton rdAdministrador;
    @FXML
    private ToggleGroup oficio;
    @FXML
    private RadioButton rdTrabajador;
    @FXML
    private Pane paneShowMSJ;
    @FXML
    private Label txtTitleShowMSJ;
    @FXML
    private Label txtMsjShow;
    private String clave = "";
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<virtualtravels.model.adminVT> adm = ObtenerClaveEmpresa();
        adm.forEach((clv) -> {
            clave = clv.getClaveEmpresa();
            System.out.println(clave);
        });
        paneOLVContrasena.setVisible(false);
        imgContrasenaIncorrecta.setVisible(false);
        lblMSJ.setText("");
        this.paneShowMSJ.setVisible(false);

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
    private void LinkContrasenaOlvidada(ActionEvent event) throws IOException {
        paneOLVContrasena.setVisible(true);
        this.paneInicioLog.setDisable(true);
        this.hbxTOP.setDisable(true);
        this.btnSitioWeb.setDisable(true);
        apDatosRigth.setOpacity(0.9);
        logPane.setOpacity(0.9);
    }

    @FXML
    private void btnExit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void btnSitioWeb(MouseEvent event) {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop escritorio = java.awt.Desktop.getDesktop();

            if (escritorio.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("file:///E:/projetsJAvaFx/virtualTravels/src/webSite/index.html");
                    escritorio.browse(uri);
                } catch (Exception ex) {
                    System.out.println("error al mostrar archivo" + ex.getMessage() + new Exception("Error al abrir archivo"));
                    JOptionPane.showMessageDialog(null, "Erro al abrir sitio web", "error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    @FXML
    private void btnMinimizar(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage ventanaACt = (Stage) source.getScene().getWindow();
        ventanaACt.setIconified(true);
    }

    @FXML
    private void btnRegistrar(ActionEvent event) throws IOException {
        AnchorPane log = FXMLLoader.load(getClass().getResource("/login/registro.fxml"));
        logPane.getChildren().setAll(log);
    }

    private void claveEmpresa(String clave) {//EN PROCESO _--__- SIN TERMINAR _-_ - - - - _ _ _ _ - - - - _
        ObservableList<virtualtravels.model.adminVT> adm = ObtenerClaveEmpresa();
        // String claveEmpresa;
        adm.forEach((bus) -> {
            String cao = bus.getClaveEmpresa();
            if (clave == cao) {

            }
        });
    }

    @FXML
    private void btnIngresarOLV(ActionEvent event) {
        ObservableList<virtualtravels.model.Usuarios> usu = ObtenerUsuarioOLV();
        //---
        String txtClave = txtClave_paneOlvContra.getText();
        if (usu.isEmpty()) {
            lblMSJ.setText("*Usuario no encontrado*");
            imgContrasenaIncorrecta.setVisible(true);
        } else {
            if (!usu.isEmpty()) {
                usu.forEach((com) -> {
                    if (txtClave.equals(clave)) {
                        try {
                            String usuario, nombreCompleto, correo;
                            usuario = com.getNombre() + " " + com.getApePaterno();
                            nombreCompleto = com.getNombre() + " " + com.getApePaterno() + " " + com.getApeMaterno();
                            correo = com.getUsuario();
                            FXMLLoader home = new FXMLLoader(getClass().getResource("/virtualtravels/Home.fxml"));
                            Parent root = home.load();
                            //--_--
                            HomeController controlador = home.getController();
                            controlador.nombreUsu(usuario, nombreCompleto, correo);
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setTitle("Virtual Travels");
                            Image ico = new Image("iconos/logVT.png");
                            stage.getIcons().add(ico);
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.show();
                            //cerrar inicio
                            Node source = (Node) event.getSource();
                            Stage ventanaACt = (Stage) source.getScene().getWindow();
                            ventanaACt.close();
                        } catch (IOException ex) {
                            System.out.println("error -> " + ex.getMessage() + "\nend");
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("ERROR CON CLAVE DE EMPRESA");
                        alert.setContentText("la clave no es correcta");
                        alert.showAndWait();
                    }
                });
            } else {
                System.out.println("clave empresa incorrecta");
            }
        }
    }

    @FXML
    private void txtUsuario_paneOlvCont(MouseEvent event) {
        imgContrasenaIncorrecta.setVisible(false);
        this.txtUsuario_paneOlvCont.setText("");
        this.txtClave_paneOlvContra.setText("");
        lblMSJ.setText("");
    }

    @FXML
    private void btnClosePaneOLV(ActionEvent event) {
        paneOLVContrasena.setVisible(false);
        this.paneInicioLog.setDisable(false);
        this.hbxTOP.setDisable(false);
        this.btnSitioWeb.setDisable(false);
        apDatosRigth.setOpacity(1);
        logPane.setOpacity(1);
    }

    public ObservableList<virtualtravels.model.Usuarios> ObtenerUsuarioOLV() {//Obteniendo los usuarios de la BD
        ObservableList<virtualtravels.model.Usuarios> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM usuarios where usuarios = '" + txtUsuario_paneOlvCont.getText() + "';";
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
//mensajes de alerta

    private void ShowMessage(String title, String message) {
        this.paneInicioLog.setDisable(true);
        this.hbxTOP.setDisable(true);
        this.btnSitioWeb.setDisable(true);
        apDatosRigth.setOpacity(0.9);
        logPane.setOpacity(0.9);
        //mostrar mensaje
        this.paneShowMSJ.setVisible(true);
        this.txtTitleShowMSJ.setText(title);
        txtTitleShowMSJ.setStyle("-fx-text-fill: black;");
        this.txtMsjShow.setText(message);
        txtMsjShow.setStyle("-fx-text-fill: black;");
    }

    @FXML
    private void btnAceptarShowMSJ(ActionEvent event) {
        //  vt.getKeyCode() == KeyEvent.VK_ENTER
        this.paneShowMSJ.setVisible(false);
        this.paneInicioLog.setDisable(false);
        this.hbxTOP.setDisable(false);
        this.btnSitioWeb.setDisable(false);
        apDatosRigth.setOpacity(1);
        logPane.setOpacity(1);
    }

    @FXML
    private void btnCloseShowMSJ(ActionEvent event) {
        this.paneShowMSJ.setVisible(false);
        this.paneInicioLog.setDisable(false);
        this.hbxTOP.setDisable(false);
        this.btnSitioWeb.setDisable(false);
        apDatosRigth.setOpacity(1);
        logPane.setOpacity(1);
    }

    @FXML
    private void btnConfiGeneral(ActionEvent event) throws IOException {
        AnchorPane config = FXMLLoader.load(getClass().getResource("/soporte/ConfigGeneral.fxml"));
        logPane.getChildren().setAll(config);
    }
}
