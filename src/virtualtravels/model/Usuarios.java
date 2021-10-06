/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels.model;

public class Usuarios {
    
    private Integer id;
    private String Nombre;
    private String ApePaterno;
    private String ApeMaterno;
    private String usuario;
    private String contrasena;

    public Usuarios(int id, String Nombre, String ApePaterno, String ApeMaterno, String usuario, String contrasena) {
        this.id = id;
        this.Nombre = Nombre;
        this.ApePaterno = ApePaterno;
        this.ApeMaterno = ApeMaterno;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuarios() {
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApePaterno() {
        return ApePaterno;
    }

    public void setApePaterno(String ApePaterno) {
        this.ApePaterno = ApePaterno;
    }

    public String getApeMaterno() {
        return ApeMaterno;
    }

    public void setApeMaterno(String ApeMaterno) {
        this.ApeMaterno = ApeMaterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
   
    
    
    

