package soporte;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class RedesSocialesController implements Initializable {

    @FXML
    private WebView webView;

    private int ref;
    @FXML
    private Pane paneInfo;
    @FXML
    private Pane eft;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.paneInfo.setVisible(false);
        eft.setVisible(false);
    }

    public void parametros(int red) {
        System.out.println("" + red);
        ref=red;
        switch (red) {
            case 1:
                String facebook = "https://www.facebook.com/";
                WebEngine fb = webView.getEngine();
                fb.load(facebook);
                break;
            case 2:
                String instagram = "http://www.instagram.com/";
                WebEngine inst = webView.getEngine();
                inst.load(instagram);
                break;
            case 3:
                String linkedin = "http://www.linkedin.com/";
                WebEngine in = webView.getEngine();
                in.load(linkedin);
                break;
            default:
                String gmail = "http://www.gmail.com/";
                WebEngine g = webView.getEngine();
                g.load(gmail);
                break;
        }

    }
    @FXML
    public void refresh() {
        switch (ref) {
            case 1:
                String facebook = "https://www.facebook.com/";
                WebEngine fb = webView.getEngine();
                fb.load(facebook);
                break;
            case 2:
                String instagram = "http://www.instagram.com/";
                WebEngine inst = webView.getEngine();
                inst.load(instagram);
                break;
            case 3:
                String linkedin = "http://www.linkedin.com/";
                WebEngine in = webView.getEngine();
                in.load(linkedin);
                break;
            case 4:
                String gmail = "http://www.gmail.com/";
                WebEngine g = webView.getEngine();
                g.load(gmail);
                break;
            default:
                String ubicacion = "https://www.google.com/maps";
                WebEngine ubi = webView.getEngine();
                ubi.load(ubicacion);
                break;
        }

    }

    @FXML
    private void btnInfo(ActionEvent event) {
        if(paneInfo.isVisible()){
            this.paneInfo.setVisible(false);
            this.webView.setOpacity(1);
            eft.setVisible(false);
        }else{
            this.webView.setOpacity(0.75);
            eft.setVisible(true);
            this.paneInfo.setVisible(true);
        }
    }

}
