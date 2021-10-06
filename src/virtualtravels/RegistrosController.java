package virtualtravels;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import virtualtravels.model.Producto;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class RegistrosController implements Initializable {

    String TipoClase;
    private String updatePSJ;

    @FXML
    private AnchorPane Apane;
    @FXML
    private ComboBox cbxDestinos;
    @FXML
    private TextField txtCodigoGen;
    @FXML
    private ComboBox cbxOrigen;
    @FXML
    private Label labelPasajesDisponibles;
    @FXML
    private TextField txtprecio;
    @FXML
    private RadioButton rbClaseA;
    @FXML
    private RadioButton rbClaseB;
    @FXML
    private RadioButton rbClaseC;
    @FXML
    private DatePicker calIda;
    @FXML
    private DatePicker calVuelta;
    @FXML
    private TextField txtNumPasaje;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtDni;
    @FXML
    private ToggleGroup s;
    @FXML
    private Pane paneGenerandoBoleto;
    @FXML
    private Label lblPorcentaje;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> dest = FXCollections.observableArrayList("Lima", "Cusco", "Ica", "Tarapoto", "Arequipa", "Puno", "Tumbes",
                "Huascaran", "Iquitos", "Madre de Dios");
        cbxDestinos.setItems(dest);
        cbxOrigen.setItems(dest);
        this.paneGenerandoBoleto.setVisible(false);
    }

    @FXML
    private void btnGenerarcod(ActionEvent event) {//Codigo de Viajes
        //Generando codigo aleatorio a trabes de la biblioteca Random
        Random codigo = new Random();
        int cd = codigo.nextInt(999999);
        int secCdg = codigo.nextInt(9999);
        String capONE = Integer.toString(cd);
        String capTWO = Integer.toString(secCdg);
        txtCodigoGen.setText(capONE + "-VT-" + capTWO + "Q");
    }

    @FXML
    private void generarBoleto(ActionEvent event) throws IOException {
        // buscando el  id del  cliente apartir del dni 
        paneGenerandoBoleto.setVisible(true);
        pausa(5);
        ObservableList<virtualtravels.model.Cliente> list = obtenerClienteDni();
        String capDni = txtDni.getText();
        String capPrecio = txtprecio.getText();
        if (capDni.equals("") || capPrecio.equals("")) {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Completar requisitos");
            tray.setMessage("llenar casillas");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2500));
        } else {
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "el dni ingresado no se encuentra registrado");
            } else {

                list.forEach((item) -> {
                    try {
                        int idCliente = item.getIdCliente();
                        String query = "INSERT INTO venta(iduserclientes,nombresApellidos,dniVenta,numVenta,destino,origen,fechaIda,fechaVuelta,clase,precioviaje,codigo) VALUES ("
                                + "'" + idCliente
                                + "','" + txtNombres.getText()
                                + "','" + txtDni.getText()
                                + "','" + txtNumPasaje.getText()
                                + "','" + cbxDestinos.getValue()
                                + "','" + cbxOrigen.getValue()
                                + "','" + calIda.getValue()
                                + "','" + calVuelta.getValue()
                                + "','" + TipoClase
                                + "','" + txtprecio.getText()
                                + "','" + txtCodigoGen.getText() + "');";

                        //actualizando pasajes disponibles
                        //METODO
                        String queryPSJ = "update producto set pasajes='" + updatePSJ + "' where lugar='" + cbxDestinos.getValue() + "';";
                        executeQuery(queryPSJ);
                        executeQuery(query);

                        TrayNotification tray = new TrayNotification();
                        AnimationType type = AnimationType.POPUP;
                        tray.setAnimationType(type);
                        tray.setTitle("Boleto generado");
                        tray.setMessage("codigo de vuelo: " + txtCodigoGen.getText());
                        tray.setNotificationType(NotificationType.SUCCESS);
                        tray.showAndDismiss(Duration.millis(3000));

                        System.out.println(query);
                        AnchorPane boleto = FXMLLoader.load(getClass().getResource("Boleto.fxml"));
                        Apane.getChildren().setAll(boleto);
                    } catch (IOException ex) {
                        System.out.println("error -> " + ex.getMessage());
                    }
                });
            }
        }
    }

    private void pausa(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
            System.out.println("pin : " + seconds);
        } catch (Exception e) {
            System.out.println("erro al pausar programa");
        }
    }

    public String operacionPSJdisponibles() {
        ObservableList<virtualtravels.model.Producto> producto = obtenerProducto();
        int capPsj = Integer.parseInt(txtNumPasaje.getText());
        if (producto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se  selecionó un destino");
        } else {
            producto.forEach((psj -> {
                int psjDisp = Integer.parseInt(psj.getPasajes());
                int diferencia = psjDisp - capPsj;
                updatePSJ = Integer.toString(diferencia);
                System.out.println("" + updatePSJ);
            }));
        }
        return updatePSJ;
    }

    // Metodo buscar cliente por dni 
    public ObservableList<virtualtravels.model.Cliente> obtenerClienteDni() {
        //buscar en la base de datos apartir del DNI y capturar el id
        ObservableList<virtualtravels.model.Cliente> clienteList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();
        String query = "SELECT * FROM userclientes where dni = '" + txtDni.getText() + "';";

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

    public ObservableList<Producto> obtenerProducto() {
        //Obtener atributos de la tabla de productos a partir del producto seleccioneado + capturar ID
        ObservableList<virtualtravels.model.Producto> ListPasajes = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM producto where lugar = '" + cbxDestinos.getValue() + "';";

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.Producto producto;
            while (rs.next()) {
                producto = new virtualtravels.model.Producto(
                        rs.getInt("idProducto"),
                        rs.getString("lugar"),
                        rs.getString("pasajes"));
                ListPasajes.add(producto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ListPasajes;

    }

    @FXML
    private void btnCalcularPrecio(ActionEvent event) {
        if (rbClaseA.isSelected()) {
            TipoClase = "A";
        } else if (rbClaseB.isSelected()) {
            TipoClase = "B";
        } else if (rbClaseC.isSelected()) {
            TipoClase = "C";
        }
        //Calculando precio de la venta de acuerdo a la clase seleccionada
        int precioBase = 100;
        int pasajeros = Integer.parseInt(txtNumPasaje.getText());
        if (rbClaseA.isSelected()) {
            txtprecio.setText(String.valueOf(precioBase * 2.5 * pasajeros));
        } else if (rbClaseB.isSelected()) {
            txtprecio.setText(String.valueOf(precioBase * 2.9 * pasajeros));
        } else if (rbClaseC.isSelected()) {
            txtprecio.setText(String.valueOf(precioBase * 4 * pasajeros));
        }

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
    private void cbxDestinos(ActionEvent event) {
        ObservableList<virtualtravels.model.Producto> list = obtenerProducto();
        list.forEach((item) -> {
            labelPasajesDisponibles.setText(item.getPasajes());
        });
    }

}
