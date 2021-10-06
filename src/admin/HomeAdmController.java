package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class HomeAdmController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    //atributos handler window
    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Notificación
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        //capurando msj
        tray.setAnimationType(type);
        tray.setTitle("Area de Administración");
        tray.setMessage("·CEO·");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
        //fin notificacion
    }

    private void cargarPg(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeAdmController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void btnClientes(ActionEvent event) {
        cargarPg("/virtualtravels/userCliente");
    }

    @FXML
    private void btnExit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/loginInicio.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Virtual Travels");
        Image ico = new Image("iconos/logVT.png");
        stage.getIcons().add(ico);
        stage.resizableProperty().setValue(Boolean.FALSE); // deshabilitar maximizar 
        stage.setScene(scene);
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        //cerrar ventana login
        Node source = (Node) event.getSource();
        Stage ventanaACt = (Stage) source.getScene().getWindow();
        ventanaACt.close();
    }

    @FXML
    private void btnHomeAdm(ActionEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void btnTrabajadores(ActionEvent event) {
        cargarPg("/virtualtravels/UsuarioSistem");
    }

    @FXML
    private void btnProductos(ActionEvent event) {
        cargarPg("/virtualtravels/lugares");
    }

    @FXML
    private void btnConfig(ActionEvent event) {
        cargarPg("/admin/configuracionAdm");
    }

    @FXML
    private void elcUsuarios(MouseEvent event) {
        cargarPg("/virtualtravels/UsuarioSistem");
    }

    @FXML
    private void elcConfiguraciones(MouseEvent event) {
        cargarPg("/admin/configuracionAdm");
    }

    @FXML
    private void elcClientes(MouseEvent event) {
        cargarPg("/virtualtravels/userCliente");
    }

    @FXML
    private void elcProductos(MouseEvent event) {
        cargarPg("/virtualtravels/lugares");
    }

    @FXML
    private void btnReportes(ActionEvent event) {
        cargarPg("/admin/estadistyReport");
    }

    @FXML
    private void elcEstadistyReporte(MouseEvent event) {
        cargarPg("/admin/estadistyReport");
    }

}
