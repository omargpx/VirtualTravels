package virtualtravels.model;

/**
 *
 * @author omar
 */
public class venta extends Cliente {

    private int idVenta;
    private int iduserclientes;
    private String nombresApellidos;
    private String dniVenta;
    private String numVenta;
    private String destino;
    private String origen;
    private String fechaIda;
    private String fechaVuelta;
    private String clase;
    private String preciopasaje;
    private String codigo;

    public venta(int idVenta, int iduserclientes, String nombresApellidos, String dniVenta, String numVenta, String destino, String origen, String fechaIda, String fechaVuelta, String clase, String preciopasaje, String codigo, int idCliente, String NombreCliente, String ApePaternoCliente, String ApeMaternoCliente, String telefono, String email, String sexo, String dni) {
        super(idCliente, NombreCliente, ApePaternoCliente, ApeMaternoCliente, telefono, email, sexo, dni);
        this.idVenta = idVenta;
        this.iduserclientes = iduserclientes;
        this.nombresApellidos = nombresApellidos;
        this.dniVenta = dniVenta;
        this.numVenta = numVenta;
        this.destino = destino;
        this.origen = origen;
        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
        this.clase = clase;
        this.preciopasaje = preciopasaje;
        this.codigo = codigo;
    }

    public venta(int idVenta, int iduserclientes, String nombresApellidos, String dniVenta, String numVenta, String destino, String origen, String fechaIda, String fechaVuelta, String clase, String preciopasaje, String codigo, String NombreCliente, String ApePaternoCliente, String ApeMaternoCliente, String telefono, String email, String sexo, String dni) {
        this.idVenta = idVenta;
        this.iduserclientes = iduserclientes;
        this.nombresApellidos = nombresApellidos;
        this.dniVenta = dniVenta;
        this.numVenta = numVenta;
        this.destino = destino;
        this.origen = origen;
        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
        this.clase = clase;
        this.preciopasaje = preciopasaje;
        this.codigo = codigo;
    }

    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIduserclientes() {
        return iduserclientes;
    }

    public void setIduserclientes(int iduserclientes) {
        this.iduserclientes = iduserclientes;
    }

    public String getNumVentas() {
        return numVenta;
    }

    public void setNumVentas(String numVenta) {
        this.numVenta = numVenta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(String fechaIda) {
        this.fechaIda = fechaIda;
    }

    public String getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(String fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getPreciopasaje() {
        return preciopasaje;
    }

    public void setPreciopasaje(String preciopasaje) {
        this.preciopasaje = preciopasaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getDniVenta() {
        return dniVenta;
    }

    public void setDniVenta(String dniVenta) {
        this.dniVenta = dniVenta;
    }
    
}
