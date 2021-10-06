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
public class adminVT {
    private Integer id;
    private String usuarioAdm;
    private String contrasena;
    private String claveEmpresa;
    
    public adminVT(Integer id, String usuarioAdm, String contrasena,String claveEmpresa){
        this.id=id;
        this.usuarioAdm=usuarioAdm;
        this.contrasena=contrasena;
        this.claveEmpresa=claveEmpresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuarioAdm() {
        return usuarioAdm;
    }

    public void setUsuarioAdm(String usuarioAdm) {
        this.usuarioAdm = usuarioAdm;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getClaveEmpresa() {
        return claveEmpresa;
    }

    public void setClaveEmpresa(String claveEmpresa) {
        this.claveEmpresa = claveEmpresa;
    }
    
}
