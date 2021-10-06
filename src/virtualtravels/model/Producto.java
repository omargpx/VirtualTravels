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
public class Producto {
    
    Integer  idProducto;
    String lugar;
    String pasajes;

    public Producto(Integer idProducto, String lugar, String pasajes) {
        this.idProducto = idProducto;
        this.lugar = lugar;
        this.pasajes = pasajes;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getPasajes() {
        return pasajes;
    }

    public void setPasajes(String pasajes) {
        this.pasajes = pasajes;
    }
    
    
  
    
}
