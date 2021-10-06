/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

import javafx.collections.ObservableList;

/**
 *
 * @author Joseph Cruz
 */
public interface Icrud {
    //metodos para el uso del ICRUD
    public void insertar();
    public void actualizar();
    public void eliminar();
    public ObservableList<virtualtravels.model.Usuarios> obtenerUsuario();
    public ObservableList<virtualtravels.model.Cliente> obtenerCliente();
    public void executeQuery(String query);   
    public void mostrarDatos();  
    public void seleccionColumna();
    
}
