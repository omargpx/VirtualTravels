/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

import virtualtravels.model.Usuarios;
import javafx.collections.ObservableList;

/**
 *
 * @author omar
 */
public interface IUsuarioDao {
    public ObservableList<Usuarios> ObtenerUsuarios();
}
