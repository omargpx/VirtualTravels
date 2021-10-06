/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class MarketVTController implements Initializable {

    @FXML
    private WebView marketWebV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine loadP= marketWebV.getEngine();
        loadP.load("file:///E:/projetsJAvaFx/virtualTravels/src/webSite/index.html");
    }    
    
}
