/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import virtualtravels.HomeController;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class BienbenidaUserController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Pane paneBienbenida;
    @FXML
    private Text txtBienvenida;
    @FXML
    private Pane panePerfil;
    @FXML
    private Circle circlePerfil;
    @FXML
    private Button btnSiguiente;
    @FXML
    private ImageView imgCheck;
    @FXML
    private Label lblCorreo;
    public String nombreUsu = "";
    public String correo = "";
    public String nombresCompletos = "";
    @FXML
    private HBox hbxTOP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.panePerfil.setVisible(false);
        Image perf = new Image("/Img/foto-de-perfil.jpg");
        this.circlePerfil.setFill(new ImagePattern(perf));
    }

    public void parametros(String nombre, String ApeP, String ApeM, String user) {
        this.lblCorreo.setText(user);
        this.txtBienvenida.setText("¡Bienvenido, " + nombre +" "+ ApeP + "!");
        //para home
        nombreUsu = nombre + " " + ApeP;
        nombresCompletos = nombre + " " + ApeP + " " + ApeM;
        correo = user;
    }

    private void pausa(int segundo) {
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception e) {
            System.out.println("eroor de pausa");
        }
    }

    @FXML
    private void btnOmitir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/virtualtravels/Home.fxml"));
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
    }

    @FXML
    private void btnSiguiente(ActionEvent event) throws IOException {
        if (!panePerfil.isVisible()) {
            panePerfil.setVisible(true);
            this.btnSiguiente.setText("Iniciar");
            this.paneBienbenida.setVisible(false);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/virtualtravels/Home.fxml"));
            Parent root = loader.load();
            //Notificación -__-
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("BIENVENIDO " + nombreUsu);
            tray.setMessage("" + correo);
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
            controlador.nombreUsu(nombreUsu, nombresCompletos, correo);
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
        }
    }

    @FXML
    private void linkPerfil(ActionEvent event) {
        FileChooser im = new FileChooser();
        im.setTitle("Imagen de perfil");
        Window ownerWindow = null;
        im.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File urls = im.showOpenDialog(ownerWindow);
        if (urls == null) {
            System.out.println("selleccionar imagen");
        } else {
            urls.getAbsoluteFile();
            System.out.println(urls);
            try {
                Image perfil = new Image(urls.toURL().toString());
                circlePerfil.setFill(new ImagePattern(perfil));
            } catch (Exception e) {
                System.out.println("erro -> " + e.getMessage());
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
    private void btnExit(MouseEvent event) {
        System.exit(0);
    }

}
