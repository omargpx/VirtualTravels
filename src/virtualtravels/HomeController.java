package virtualtravels;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import virtualtravels.model.Producto;

public class HomeController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label nombreUsuDash;
    @FXML
    private Label lblPasajesLima;
    @FXML
    private Label lblPasajesCusco;
    @FXML
    private Label lblPasajesIca;
    @FXML
    private Label lblPasajesTarapoto;
    @FXML
    private Label lblPasajesArequipa;
    @FXML
    private Text txtLima;
    @FXML
    private Text txtCusco;
    @FXML
    private Text txtIca;
    @FXML
    private Text txtTarapoto;
    @FXML
    private Text txtArequipa;
    @FXML
    private VBox vbxNavigation;
    @FXML
    private VBox barraDTBase;
    @FXML
    private HBox dtBase;
    @FXML
    private VBox barraConex;
    @FXML
    private VBox barraBDTables;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblCorreo;
    @FXML
    private Pane paneInfoUsu;
    @FXML
    private ImageView ntfActivo;
    @FXML
    private Pane paneNotificacion;
    @FXML
    private Label lblSinNotificacion;
    @FXML
    private Label tituloEmrgNTF;
    @FXML
    private Label tipoNTFemergente;
    @FXML
    private Label parteMSJemergente;
    @FXML
    private Label lblFechaEmergenteNTF;
    @FXML
    private Pane paneNTFEmergente;
    @FXML
    private Pane paneNTFnormal;
    @FXML
    private Label tituloNTFnormal;
    @FXML
    private Label tipoNTFNormal;
    @FXML
    private Text msjNTFnormal;
    @FXML
    private Label fechaNTFnormal;
    @FXML
    private ImageView firstIMGView;
    @FXML
    private Circle circlePerfil;
    @FXML
    private Label lblNombreUsu;
    @FXML
    private Circle circlePerfilPane;
    @FXML
    private Circle circlePerfilP;
    @FXML
    private Label lblNameUsu;
    @FXML
    private ImageView btnConfiUsu;
    //Atributos para mover ventana
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Button btnDestinos;
    @FXML
    private Button btnEnvios;
    @FXML
    private Button btnBoleto;
    @FXML
    private Button btnUsuarioCliente;
    @FXML
    private Button btnProducto;
    @FXML
    private Button btnNewDocument;
    @FXML
    private Button btnOfertas;
    @FXML
    private Button btnAcercade;
    @FXML
    private Button btnProductosFront;
    @FXML
    private HBox btnBoletoFront;
    @FXML
    private HBox btnClientes;
    @FXML
    private Button btnUbicación;
    @FXML
    private Button btnMarketWeb;

    public void nombreUsu(String nombreUsu, String NombreCompleto, String correo) {
        this.nombreUsuDash.setText(NombreCompleto);
        this.lblNombreUsu.setText(nombreUsu);
        this.lblNombreUsuario.setText(NombreCompleto);
        this.lblCorreo.setText(correo);
        this.lblNameUsu.setText(nombreUsu);
    }

    public void perfil(String urlImg) throws MalformedURLException {
        System.out.println("" + urlImg);
        File url = new File(urlImg);
        Image perfil = new Image(url.toURL().toString());
        circlePerfil.setFill(new ImagePattern(perfil));
        circlePerfilPane.setFill(new ImagePattern(perfil));
        circlePerfilP.setFill(new ImagePattern(perfil));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnConfiUsu.setVisible(false);
        //NOTIFICACIONES Y COMUNICADOS
        ObservableList<virtualtravels.model.comunicados> comunicados = ObtenerComunicados();
        ObservableList<virtualtravels.model.comunicados> ntfEmergente = ObtenerComunicadoEmergente();
        ObservableList<virtualtravels.model.comunicados> ntfNormal = ObtenerComunicadoNormal();
        if (comunicados.isEmpty()) {
            lblSinNotificacion.setVisible(true);
            ntfActivo.setVisible(false);
            paneNTFEmergente.setVisible(false);
            paneNTFnormal.setVisible(false);
        } else {
            lblSinNotificacion.setVisible(false);
            ntfActivo.setVisible(true);
            if (!ntfEmergente.isEmpty()) {
                paneNTFEmergente.setVisible(true);
                ntfEmergente.forEach((emg) -> {
                    this.tituloEmrgNTF.setText(emg.getTitulo());
                    this.lblFechaEmergenteNTF.setText(emg.getFecha());
                    this.tipoNTFemergente.setText("· " + emg.getEstado());
                    this.parteMSJemergente.setText(emg.getMensaje());
                });
            } else {
                paneNTFEmergente.setVisible(true);
            }
            if (!ntfNormal.isEmpty()) {
                paneNTFnormal.setVisible(true);
                ntfNormal.forEach((normal) -> {
                    this.tipoNTFNormal.setText("· " + normal.getEstado());
                    this.tituloNTFnormal.setText(normal.getTitulo());
                    this.msjNTFnormal.setText(normal.getMensaje());
                    this.fechaNTFnormal.setText(normal.getFecha());
                });
            } else {
                paneNTFnormal.setVisible(false);
            }
        }
        paneNotificacion.setVisible(false);
        this.paneInfoUsu.setVisible(false);
        //barras
        this.barraBDTables.setVisible(false);
        this.barraConex.setVisible(false);
        this.barraDTBase.setVisible(false);
        //pasajes disponibles
        ObservableList<virtualtravels.model.Producto> listLima = obtenerProductoLima();
        listLima.forEach((item) -> {
            lblPasajesLima.setText(item.getPasajes());
        });
        //Cusco
        ObservableList<virtualtravels.model.Producto> listCusco = obtenerProductoCusco();
        listCusco.forEach((item) -> {
            lblPasajesCusco.setText(item.getPasajes());
        });
        //ICA
        ObservableList<virtualtravels.model.Producto> listIca = obtenerProductoIca();
        listIca.forEach((item) -> {
            lblPasajesIca.setText(item.getPasajes());
        });
        //TARAPOTO
        ObservableList<virtualtravels.model.Producto> listTarapoto = obtenerProductoTarapoto();
        listTarapoto.forEach((item) -> {
            lblPasajesTarapoto.setText(item.getPasajes());
        });
        //AREQUIPA
        ObservableList<virtualtravels.model.Producto> listArequipa = obtenerProductoArequipa();
        listArequipa.forEach((item) -> {
            lblPasajesArequipa.setText(item.getPasajes());
        });
    }

    //metodo para llamr a las clases a partir de esta accion
    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void home(MouseEvent event) throws IOException {
        bp.setCenter(ap);
    }

    @FXML
    private void btnDestinos(MouseEvent event) throws IOException {
        loadPage("/virtualtravels/registros");
    }

    @FXML
    private void btnEnvios(MouseEvent event) {
        loadPage("/virtualtravels/envios");
    }

    private void btnSoporte(MouseEvent event) {
        loadPage("/soporte/ScSoporte");
    }

    @FXML
    private void btnProducto(ActionEvent event) {
        loadPage("/virtualtravels/lugares");
    }

    public ObservableList<virtualtravels.model.comunicados> ObtenerComunicados() {
        ObservableList<virtualtravels.model.comunicados> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM notificaciones";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.comunicados comunicados;
            while (rs.next()) {
                comunicados = new virtualtravels.model.comunicados(rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("mensaje"),
                        rs.getString("estado"),
                        rs.getString("fecha"));
                usuarioList.add(comunicados);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuarioList;
    }// comunicados en total

    public ObservableList<virtualtravels.model.comunicados> ObtenerComunicadoEmergente() {
        ObservableList<virtualtravels.model.comunicados> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM notificaciones where id='1';";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.comunicados comunicados;
            while (rs.next()) {
                comunicados = new virtualtravels.model.comunicados(rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("mensaje"),
                        rs.getString("estado"),
                        rs.getString("fecha"));
                usuarioList.add(comunicados);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuarioList;
    }

    public ObservableList<virtualtravels.model.comunicados> ObtenerComunicadoNormal() {
        ObservableList<virtualtravels.model.comunicados> usuarioList = FXCollections.observableArrayList();

        virtualtravels.Conexion conex = new virtualtravels.Conexion();

        Connection conn = conex.getConnection();

        String query = "SELECT * FROM notificaciones where id='2';";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            virtualtravels.model.comunicados comunicados;
            while (rs.next()) {
                comunicados = new virtualtravels.model.comunicados(rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("mensaje"),
                        rs.getString("estado"),
                        rs.getString("fecha"));
                usuarioList.add(comunicados);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuarioList;
    }

    @FXML
    private void btnBoleto(ActionEvent event) {
        loadPage("/virtualtravels/Boleto");
    }

    //obtener pasajes respectivos
    public ObservableList<Producto> obtenerProductoLima() {
        //Obtener atributos de la tabla de productos a partir del producto seleccioneado + capturar ID
        ObservableList<virtualtravels.model.Producto> ListPasajes = FXCollections.observableArrayList();
        virtualtravels.Conexion conex = new virtualtravels.Conexion();
        Connection conn = conex.getConnection();
        String query = "SELECT * FROM producto where lugar = '" + txtLima.getText() + "';";

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

    public ObservableList<Producto> obtenerProductoCusco() {
        //Obtener atributos de la tabla de productos a partir del producto seleccioneado + capturar ID
        ObservableList<virtualtravels.model.Producto> ListPasajes = FXCollections.observableArrayList();
        virtualtravels.Conexion conex = new virtualtravels.Conexion();
        Connection conn = conex.getConnection();
        String query = "SELECT * FROM producto where lugar = '" + txtCusco.getText() + "'";

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

    public ObservableList<Producto> obtenerProductoIca() {
        //Obtener atributos de la tabla de productos a partir del producto seleccioneado + capturar ID
        ObservableList<virtualtravels.model.Producto> ListPasajes = FXCollections.observableArrayList();
        virtualtravels.Conexion conex = new virtualtravels.Conexion();
        Connection conn = conex.getConnection();
        String query = "SELECT * FROM producto where lugar = '" + txtIca.getText() + "'";

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

    public ObservableList<Producto> obtenerProductoTarapoto() {
        //Obtener atributos de la tabla de productos a partir del producto seleccioneado + capturar ID
        ObservableList<virtualtravels.model.Producto> ListPasajes = FXCollections.observableArrayList();
        virtualtravels.Conexion conex = new virtualtravels.Conexion();
        Connection conn = conex.getConnection();
        String query = "SELECT * FROM producto where lugar = '" + txtTarapoto.getText() + "'";

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

    public ObservableList<Producto> obtenerProductoArequipa() {
        //Obtener atributos de la tabla de productos a partir del producto seleccioneado + capturar ID
        ObservableList<virtualtravels.model.Producto> ListPasajes = FXCollections.observableArrayList();
        virtualtravels.Conexion conex = new virtualtravels.Conexion();
        Connection conn = conex.getConnection();
        String query = "SELECT * FROM producto where lugar = '" + txtArequipa.getText() + "'";

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
    private void btnSitioWeb(MouseEvent event) {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop escritorio = java.awt.Desktop.getDesktop();

            if (escritorio.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("file:///E:/projetsJAvaFx/virtualTravels/src/webSite/index.html");
                    escritorio.browse(uri);
                } catch (Exception ex) {
                    System.out.println("error al mostrar archivo" + ex);
                }
            }
        }
    }

    @FXML
    private void btnProductosFront(MouseEvent event) {
        loadPage("/virtualtravels/lugares");
    }

    @FXML
    private void btnBoletoFront(MouseEvent event) {
        loadPage("/virtualtravels/Boleto");
    }

    @FXML
    private void BDExited(MouseEvent event) {
        this.barraDTBase.setVisible(false);
    }

    @FXML
    private void BDEntered(MouseEvent event) {
        this.barraDTBase.setVisible(true);
    }

    @FXML
    private void barraInfoEntered(MouseEvent event) {
        this.barraDTBase.setVisible(true);
    }

    @FXML
    private void LineTablasBDExited(MouseEvent event) {
        this.barraBDTables.setVisible(false);
    }

    @FXML
    private void LineTablasBDEntered(MouseEvent event) {
        this.barraBDTables.setVisible(true);
    }

    @FXML
    private void LineConexExited(MouseEvent event) {
        this.barraConex.setVisible(false);
    }

    @FXML
    private void LineConexEntered(MouseEvent event) {
        this.barraConex.setVisible(true);
    }

    @FXML
    private void downloadCODbd(MouseEvent event) {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop escritorio = java.awt.Desktop.getDesktop();

            if (escritorio.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("https://drive.google.com/file/d/1ammUM3kbPrz_8V5tAdA3nwN5wNQ9PXjL/view?usp=sharing");
                    escritorio.browse(uri);
                } catch (Exception ex) {
                    System.out.println("error al Abrir archivo" + ex);
                }
            }
        }
    }

    @FXML
    private void btnUbicación(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/soporte/redesSociales.fxml"));
        Parent root = loader.load();
        soporte.RedesSocialesController controlador = loader.getController();
        controlador.parametros(5);
        bp.setCenter(root);
    }

    @FXML
    private void btnMarketWeb(ActionEvent event) {
        loadPage("/virtualtravels/marketVT");
    }

    @FXML
    private void btnUsuarioCliente(ActionEvent event) {
        loadPage("/virtualtravels/userCliente");
    }

    @FXML
    private void barraInfoExit(MouseEvent event) {
        this.barraDTBase.setVisible(false);
    }

    @FXML
    private void btnOfertas(ActionEvent event) {
        loadPage("/virtualtravels/ofertas");
    }

    @FXML
    private void btnAcercade(ActionEvent event) {
        loadPage("/virtualtravels/acercaDe");
    }

    private void btnPaneUsu(ActionEvent event) {
        if (!paneInfoUsu.isVisible()) {
            this.paneInfoUsu.setVisible(true);
            paneNotificacion.setVisible(false);
        } else {
            this.paneInfoUsu.setVisible(false);
            paneNotificacion.setVisible(false);
        }
    }

    @FXML
    private void btnConfiguracion(ActionEvent event) throws IOException {
        String usuario, correo;
        usuario = lblNombreUsuario.getText();
        correo = lblCorreo.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/soporte/configuracionUsuario.fxml"));
        Parent root = loader.load();
        soporte.ConfiguracionUsuarioController controlador = loader.getController();
        Paint perf = circlePerfil.getFill();
        controlador.paramtrsBienvenida(usuario, correo, perf);
        bp.setCenter(root);
        this.paneInfoUsu.setVisible(false);
    }

    @FXML
    private void btnNewDocument(ActionEvent event) {
        loadPage("/virtualtravels/docReporte");
    }

    @FXML
    private void btnNotificacion(MouseEvent event) throws IOException {
        home(event);
        if (paneNotificacion.isVisible()) {
            paneNotificacion.setVisible(false);
            paneInfoUsu.setVisible(false);
        } else {
            paneNotificacion.setVisible(true);
            paneInfoUsu.setVisible(false);
        }
    }

    @FXML
    private void btnInfoUsu(MouseEvent event) throws IOException {
        home(event);
        if (paneInfoUsu.isVisible()) {
            paneInfoUsu.setVisible(false);
        } else {
            paneInfoUsu.setVisible(true);
            paneNotificacion.setVisible(false);
        }
    }

    @FXML
    private void contntEmailExt(MouseEvent event) {
        this.btnConfiUsu.setVisible(false);
    }

    @FXML
    private void contntEmailEnt(MouseEvent event) {
        this.btnConfiUsu.setVisible(true);
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
    private void btnCerrarSesion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/loginInicio.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Virtual Travels");
        Image ico = new Image("iconos/logVT.png");
        stage.getIcons().add(ico);
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
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        //cerrar ventana login
        Node source = (Node) event.getSource();
        Stage ventanaACt = (Stage) source.getScene().getWindow();
        ventanaACt.close();
    }

    @FXML
    private void btnClientes(MouseEvent event) {
        loadPage("/virtualtravels/userCliente");
    }

    @FXML
    private void webFacebook(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/soporte/redesSociales.fxml"));
        Parent root = loader.load();
        soporte.RedesSocialesController controlador = loader.getController();
        controlador.parametros(1);
        bp.setCenter(root);
    }

    @FXML
    private void webInstagram(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/soporte/redesSociales.fxml"));
        Parent root = loader.load();
        soporte.RedesSocialesController controlador = loader.getController();
        controlador.parametros(2);
        bp.setCenter(root);
    }

    @FXML
    private void webLinkedin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/soporte/redesSociales.fxml"));
        Parent root = loader.load();
        soporte.RedesSocialesController controlador = loader.getController();
        controlador.parametros(3);
        bp.setCenter(root);
    }

    @FXML
    private void webGmail(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/soporte/redesSociales.fxml"));
        Parent root = loader.load();
        soporte.RedesSocialesController controlador = loader.getController();
        controlador.parametros(4);
        bp.setCenter(root);
    }

    @FXML
    private void btnMensajeria(MouseEvent event) {
    }

}
