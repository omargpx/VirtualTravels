package soporte;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import virtualtravels.Conexion;
import virtualtravels.HomeController;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class ConfigGeneralController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane apMini;
    @FXML
    private Rectangle paneCap1;
    @FXML
    private Rectangle paneCap2;
    @FXML
    private ColorPicker colorpick1;
    @FXML
    private ColorPicker colorpick2;
    @FXML
    private Pane paneCustom;
    @FXML
    private ToggleButton btnGeneral;
    @FXML
    private ToggleButton btnConection;
    @FXML
    private ToggleButton btnAcercaDe;
    @FXML
    private ToggleGroup configuraciones;
    @FXML
    private JFXToggleButton darkThem;
    @FXML
    private JFXToggleButton AlwayOnTop;
    @FXML
    private HBox hbxTop;
    @FXML
    private HBox hbxBack;
    @FXML
    private VBox paneButtons;
    @FXML
    private Pane paneConnection;
    @FXML
    private JFXTextField txtBD;
    @FXML
    private JFXPasswordField pswBD;
    @FXML
    private Pane paneAcercaDe;
    @FXML
    private JFXToggleButton Togleacounts;

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnGeneral.setStyle("-fx-background-color:  #002c48b8; -fx-border-color: #2B5BBF; -fx-border-width: 0px 0px 0px 2px; -fx-text-fill:white;");
        this.paneCustom.setVisible(false);
        this.paneConnection.setVisible(false);
        this.paneAcercaDe.setVisible(false);
    }

    @FXML
    private void btnAtras(ActionEvent event) throws IOException {
        AnchorPane log = FXMLLoader.load(getClass().getResource("/login/loginInicio.fxml"));
        ap.getChildren().setAll(log);
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

    @FXML
    private void btnGeneral(ActionEvent event) {
        this.paneConnection.setVisible(false);
        this.paneAcercaDe.setVisible(false);
        bp.setCenter(apMini);
        preguntar();
    }

    @FXML
    private void btnConection(ActionEvent event) {
        this.paneConnection.setVisible(true);
        this.paneAcercaDe.setVisible(false);
        preguntar();
    }

    @FXML
    private void btnAcercaDe(ActionEvent event) {
        this.paneConnection.setVisible(false);
        this.paneAcercaDe.setVisible(true);
        preguntar();
    }

    @FXML
    private void linkEdit(ActionEvent event) {
        if (paneCustom.isVisible()) {
            this.paneCustom.setVisible(false);
        } else {
            this.paneCustom.setVisible(true);
        }

    }

    @FXML
    private void colorpick1(ActionEvent event) {
        Color color = colorpick1.getValue();
        paneCap1.setFill(color);
    }

    @FXML
    private void colorpick2(ActionEvent event) {
        Color color = colorpick2.getValue();
        paneCap2.setFill(color);
    }

    private void preguntar() {
        if (btnGeneral.isSelected()) {
            this.btnGeneral.setStyle("-fx-background-color:  #002c48b8; -fx-border-color: #2B5BBF; -fx-border-width: 0px 0px 0px 2px; -fx-text-fill:white;");
            this.btnConection.setStyle("-fx-background-color:  transparent; -fx-border-color: transparent; -fx-text-fill:white;");
            this.btnAcercaDe.setStyle("-fx-background-color:  transparent; -fx-border-color: transparent; -fx-text-fill:white;");
        } else if (btnConection.isSelected()) {
            this.btnConection.setStyle("-fx-background-color:  #002c48b8; -fx-border-color: #2B5BBF; -fx-border-width: 0px 0px 0px 2px;-fx-text-fill:white;");
            this.btnGeneral.setStyle("-fx-background-color:  transparent; -fx-border-color: transparent;-fx-text-fill:white; ");
            this.btnAcercaDe.setStyle("-fx-background-color:  transparent; -fx-border-color: transparent; -fx-text-fill:white;");
        } else if (btnAcercaDe.isSelected()) {
            this.btnAcercaDe.setStyle("-fx-background-color:  #002c48b8; -fx-border-color: #2B5BBF; -fx-border-width: 0px 0px 0px 2px;-fx-text-fill:white;");
            this.btnGeneral.setStyle("-fx-background-color:  transparent; -fx-border-color: transparent;-fx-text-fill:white; ");
            this.btnConection.setStyle("-fx-background-color:  transparent; -fx-border-color: transparent; -fx-text-fill:white;");
        }
    }

    @FXML
    private void darkThem(ActionEvent event) {
        if (darkThem.isSelected()) {
            darkThem.setText("On");
            this.paneButtons.setStyle("-fx-background-color:#272727;");
            this.hbxBack.setStyle("-fx-background-color:#272727;");
            this.apMini.setStyle("-fx-background-color:#121212 ;");
            this.hbxTop.setStyle("-fx-background-color:#121212;");
            this.ap.setStyle("-fx-background-color:#272727;");
            paneAcercaDe.setStyle("-fx-background-color:#121212 ;");
            paneConnection.setStyle("-fx-background-color:#121212 ;");
        } else {
            darkThem.setText("Off");
            this.ap.setStyle("-fx-background-color:#031024;");
            this.paneButtons.setStyle("-fx-background-color: #031024;");
            this.hbxBack.setStyle("-fx-background-color:#031024;");
            this.apMini.setStyle("-fx-background-color: #040310;");
            this.hbxTop.setStyle("-fx-background-color: #040310;");
            paneAcercaDe.setStyle("-fx-background-color:#040310 ;");
            paneConnection.setStyle("-fx-background-color:#040310 ;");
        }
    }

    @FXML
    private void AlwayOnTop(ActionEvent event) {
        if (AlwayOnTop.isSelected()) {
            Node source = (Node) event.getSource();
            Stage ventanaACt = (Stage) source.getScene().getWindow();
            ventanaACt.setAlwaysOnTop(true);
            AlwayOnTop.setText("On");
        } else {
            Node source = (Node) event.getSource();
            Stage ventanaACt = (Stage) source.getScene().getWindow();
            ventanaACt.setAlwaysOnTop(false);
            AlwayOnTop.setText("Off");
        }
    }
//styles BTNconfig

    @FXML
    private void btnGeneralExt(MouseEvent event) {
        if (btnGeneral.isSelected()) {
            preguntar();
        } else {
            this.btnGeneral.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;-fx-text-fill:white;");
        }
    }

    @FXML
    private void btnGeneralEnt(MouseEvent event) {
        if (btnGeneral.isSelected()) {
            preguntar();
        } else {
            this.btnGeneral.setStyle("-fx-background-color: #002c4861; -fx-border-color: transparent;-fx-text-fill:white; -fx-cursor:hand; ");
        }
    }

    @FXML
    private void btnConectionExt(MouseEvent event) {
        if (btnConection.isSelected()) {
            preguntar();
        } else {
            this.btnConection.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;-fx-text-fill:white;");
        }
    }

    @FXML
    private void btnConectionEnt(MouseEvent event) {
        if (btnConection.isSelected()) {
            preguntar();
        } else {
            this.btnConection.setStyle("-fx-background-color: #002c4861; -fx-border-color: transparent;-fx-text-fill:white; -fx-cursor:hand; ");
        }
    }

    @FXML
    private void btnAcercaDeExt(MouseEvent event) {
        if (btnAcercaDe.isSelected()) {
            preguntar();
        } else {
            this.btnAcercaDe.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;-fx-text-fill:white;");
        }
    }

    @FXML
    private void btnAcercaDeEnt(MouseEvent event) {
        if (btnAcercaDe.isSelected()) {
            preguntar();
        } else {
            this.btnAcercaDe.setStyle("-fx-background-color: #002c4861; -fx-border-color: transparent;-fx-text-fill:white; -fx-cursor:hand; ");
        }
    }

    @FXML
    private void btnConectar(ActionEvent event) {
        String bd, clave;
        bd = txtBD.getText();
        clave = pswBD.getText();
        virtualtravels.Conexion conex = new Conexion();
        conex.configConection(bd, clave);
    }

    @FXML
    private void btnInfo(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "virtual_travels \nberlin64");
    }

    @FXML
    private void dwloadCodeBD(ActionEvent event) {
    }

    @FXML
    private void Togleacounts(ActionEvent event) {
        if (!Togleacounts.isSelected()) {
            this.Togleacounts.setText("Off");
        } else {
            this.Togleacounts.setText("On");
        }
    }
}
