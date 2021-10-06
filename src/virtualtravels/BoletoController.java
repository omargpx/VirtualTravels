package virtualtravels;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
public class BoletoController implements Initializable {

    @FXML
    private TextField txtTiempoVuelo;
    @FXML
    private TextField txtAsiento;
    @FXML
    private TextField txtTiempoEspera;
    @FXML
    private TextField txtOrigen;
    @FXML
    private TextField txtDestino;
    @FXML
    private TextField txtNombrePasajero;
    @FXML
    private TextField txtCodigoViaje;
    @FXML
    private TextField txtClase;
    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtBuscarCodigoCliente;
    @FXML
    private TextField txtNumPasaje;
    @FXML
    private TextField txtViajeFecha;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Label fechaVenta;

    //Cpturando fecha del sistema
    Date date = new Date();
    int hora = date.getHours();
    int minuto = date.getMinutes();
    int segundo = date.getSeconds();
    String H = Integer.toString(hora);
    String Min = Integer.toString(minuto);
    String S = Integer.toString(segundo);
    @FXML
    private TextField txtCorreoCliente;
    @FXML
    private TextField txtNamePasajero;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<virtualtravels.model.venta> obtenerventa = obtenerCodigo();
        txtTiempoEspera.setText("2 Horas");
        fechaVenta.setText(fechaActual() + " - " + H + ":" + Min + ":" + S);
    }

    public static String fechaActual() { // CAPTURA DE LA FECHA ACTUAL
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }

    @FXML
    private void btnImprimirBoleto(ActionEvent event) {
    }

    @FXML
    private void btnbuscarPasajeroCodigo(MouseEvent event) {//buscar boleto reservado a traves del codigo
        ObservableList<virtualtravels.model.venta> obtenerventa = obtenerCodigo();
        if (obtenerventa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "el  codigo  no  existe");
        } else {
            obtenerventa.forEach((item) -> {
                txtClase.setText(item.getClase());
                txtOrigen.setText(item.getOrigen());
                txtDestino.setText(item.getDestino());
                txtNumPasaje.setText(item.getNumVentas());
                txtViajeFecha.setText(item.getDestino() + " " + item.getFechaIda());
                txtPrecio.setText("S/" + item.getPreciopasaje());
                txtNombrePasajero.setText(item.getNombreCliente() + " " + item.getApePaternoCliente()
                        + " " + item.getApeMaternoCliente());
                txtDNI.setText(item.getDni());
                txtCorreoCliente.setText(item.getEmail());
                txtNamePasajero.setText(item.getNombresApellidos());
                txtCodigoViaje.setText(item.getCodigo());
            });

        }

    }

    // metodo  para buscar por codigo  de reserva
    public ObservableList<virtualtravels.model.venta> obtenerCodigo() {//metodo para obtener los datos de la BD cuando el codigo sea existente
        ObservableList<virtualtravels.model.venta> codigo = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "select * from venta INNER JOIN userclientes where venta.codigo= '" + txtBuscarCodigoCliente.getText() + "';";

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.venta clienteBoleto;
            while (rs.next()) {
                clienteBoleto = new virtualtravels.model.venta(
                        rs.getInt("idVenta"),
                        rs.getInt("iduserclientes"),
                        rs.getString("nombresApellidos"),
                        rs.getString("dniVenta"),
                        rs.getString("numVenta"),
                        rs.getString("destino"),
                        rs.getString("origen"),
                        rs.getString("fechaIda"),
                        rs.getString("fechaVuelta"),
                        rs.getString("clase"),
                        rs.getString("precioviaje"),
                        rs.getString("codigo"),
                        rs.getInt("idCliente"),
                        rs.getString("nombreCliente"),
                        rs.getString("ApePaternoCliente"),
                        rs.getString("ApeMaternoCliente"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("sexo"),
                        rs.getString("dni")
                );
                codigo.add(clienteBoleto);
            }

        } catch (Exception ex) {
            System.out.println("error -> " + ex.getMessage());
        }
        return codigo;
    }

    @FXML
    private void generarAsiento(ActionEvent event) {
        ObservableList<virtualtravels.model.venta> datos = obtenerCodigo();
        datos.forEach((dt) -> {
            String clase = dt.getClase();
            Random asiento = new Random();
            int num = asiento.nextInt(30);
            txtAsiento.setText(clase + num);
        });

    }

}
