/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class OfertasController implements Initializable {

    Date date = new Date();
    int fecha = date.getMonth();
    int dia = date.getDay();
    
    @FXML
    private Pane paneHalowen;
    @FXML
    private HBox msjSinOfertas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        msjSinOfertas.setVisible(false);
        if(fecha==9){
            
        }else if(fecha==10 || fecha==11){
            
        }else if(fecha==11 && dia==30){
            
        }else if(fecha==0 && dia==0){
            
        }else if(fecha==6){
            System.out.println("FELICES FIESTAS PATRIAS");
        }else{
            this.msjSinOfertas.setVisible(true);
        }
    }    
    
}
