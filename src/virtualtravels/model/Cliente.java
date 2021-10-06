/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels.model;

/**
 *
 * @author Joseph Cruz
 */
public class Cliente {
    
    private Integer idCliente;
    private String nombreCliente;
    private String ApePaternoCliente;
    private String ApeMaternoCliente;
    private String telefono;
    private String email;
    private String sexo;
    private String dni;

    public Cliente(Integer idCliente, String nombreCliente, String ApePaternoCliente, String ApeMaternoCliente, String telefono, String email, String sexo, String dni) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.ApePaternoCliente = ApePaternoCliente;
        this.ApeMaternoCliente = ApeMaternoCliente;
        this.telefono = telefono;
        this.email = email;
        this.sexo = sexo;
        this.dni = dni;
    }
    //metodo de validación si es numero  
     public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    
    ////////////////////////////////
     public Cliente(){}

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
         
          int numero;
          if (isNumeric(NombreCliente) == true) {
            numero = Integer.parseInt(NombreCliente);
            System.out.println("Numero: " + numero);
            this.nombreCliente = "numero";
        } else {
            this.nombreCliente = NombreCliente;
        }
        
        
    }

    public String getApePaternoCliente() {
        return ApePaternoCliente;
    }

    public void setApePaternoCliente(String ApePaternoCliente) {
        this.ApePaternoCliente = ApePaternoCliente;
    }

    public String getApeMaternoCliente() {
        return ApeMaternoCliente;
    }

    public void setApeMaternoCliente(String ApeMaternoCliente) {
        this.ApeMaternoCliente = ApeMaternoCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
}
