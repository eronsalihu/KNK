package Controllers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Help {
    public void help() {
        Stage primaryStage=new Stage();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load( getClass().getResource("/Help/index.html").toString() );
        Image applicationIcon = new Image(getClass().getResourceAsStream("/Icons/icon.png"));
        primaryStage.getIcons().add(applicationIcon);
        Scene scene = new Scene(webView,600,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TEKH");
        primaryStage.show();

    }

}
