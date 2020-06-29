package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.ResourceBundle;

public class HomePane implements Initializable, Runnable {

    @FXML
    private Text time;

    @FXML
    private Text period;

    @FXML
    private TextField serachBar;

    @FXML
    private Button searchBtn;

    @FXML
    ImageView img;

    private Calendar time_system;

    private SimpleDateFormat sdf_time;

    private SimpleDateFormat sdf_period;

    private Thread interna;

    private String[] img_background;

    private boolean stop;

    private Random random_img;

    public HomePane() {
        sdf_time = new SimpleDateFormat("KK:mm");

        sdf_period = new SimpleDateFormat("a");

        interna = null;

        stop = false;

        interna = new Thread(this, "Relogio");

        img_background = new String[3];

        img_background[0] = "/Help/img/diagoona-bg-1.jpg";
        img_background[1] = "/Help/img/diagoona-bg-2.jpg";
        img_background[2] = "/Help/img/diagoona-bg-3.jpg";


        random_img = new Random();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        img.setImage( new Image( img_background[ random_img.nextInt( 1 ) ] ) );

        time.setText(formatTextTime());

        period.setText(formatTextPeriod());

        interna.start();


        searchBtn.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage=new Stage();
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            String web="https://www.google.com/search?q=";
            web=web+serachBar.getText();
            webEngine.load(web);



            Image applicationIcon = new Image(getClass().getResourceAsStream("/Icons/icon.png"));
            primaryStage.getIcons().add(applicationIcon);
            Scene scene = new Scene(webView,900,600);

            primaryStage.setScene(scene);
            primaryStage.setTitle("TEKH");
            primaryStage.show();

        });
    }

    @Override
    public void run() {
        while (Thread.currentThread() == interna && !stop) {
            try {
                Thread.sleep(1000);

                time.setText(formatTextTime());

                period.setText(formatTextPeriod());
            } catch (InterruptedException e) {
                // TODO
            }
        }
    }

    public void stop() {
        stop = true;
        interna = null;
    }

    public String formatTextTime() {
        time_system = new GregorianCalendar();

        return sdf_time.format(time_system.getTime());
    }


    public String formatTextPeriod() {
        return sdf_period.format(time_system.getTime());
    }
}