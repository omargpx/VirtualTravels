/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels.model;

/**
 *
 * @author omar
 */
public class envios {
    String nombre;
    String aPaterno;
    String aMaterno;
    Integer dni;
    String destino;
    String peso;
    String de;
    String para;
    String dniRe;

    public envios(String nombre, String aPaterno, String aMaterno, Integer dni, String destino, String peso, String de, String para, String dniRe) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.dni = dni;
        this.destino = destino;
        this.peso = peso;
        this.de = de;
        this.para = para;
        this.dniRe = dniRe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getDniRe() {
        return dniRe;
    }

    public void setDniRe(String dniRe) {
        this.dniRe = dniRe;
    }
    
}
