package Controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.stream.StreamFilter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private ImageView menu;

    @FXML
    private Label language;

    @FXML
    private Label alBtn;

    @FXML
    private Label enBtn;

    @FXML
    private Label accLabel;

    @FXML
    private Label email;

    @FXML
    private StackPane stackPane;

    @FXML
    private Pane rightPane;


    @FXML
    private Button home;

    @FXML
    private Button stock;

    @FXML
    private Button orders;

    @FXML
    private Button payment;
	
    @FXML
    private AnchorPane ankori;


    @FXML
    private Label signOut;

    @FXML
    private Label changePassword;

    @FXML
    private Label tekhLabel;

    @FXML
    private Label helpBtn;

    @FXML
    private VBox vMenu;

    @FXML
    private VBox vMenu1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signOut.setOnMouseClicked(mouseEvent -> {
            Parent singUp = null;
            try {
                singUp = FXMLLoader.load(getClass().getResource("/Views/SignIn.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene singUpScene = new Scene(singUp);

            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

            window.setScene(singUpScene);
            window.show();
        });
        vMenu1.setVisible(false);

        changePassword.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ChangePassword.fxml"));
            Parent changePwd = null;
            try {
                changePwd = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image applicationIcon = new Image(getClass().getResourceAsStream("/Icons/icon.png"));
            Stage stage = new Stage();
            stage.setTitle("TEKH");
            stage.getIcons().add(applicationIcon);
            stage.setScene(new Scene(changePwd));
            stage.show();

        });
	
ankori.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.H,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                Help help=new Help();
                help.help();
                }
            }
        });
        ankori.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.H,
                    KeyCombination.SHIFT_DOWN);
            public void handle(KeyEvent ke) {

                if (keyComb.match(ke)) {
                    try
                    {
                        Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/HomePane.fxml"));
                        rightPane.getChildren().add(stockPane);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        ankori.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.S,
                    KeyCombination.SHIFT_DOWN);
            public void handle(KeyEvent ke) {

                if (keyComb.match(ke)) {
                    try
                    {
                        Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/StockPane.fxml"));
                        rightPane.getChildren().add(stockPane);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        ankori.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.O,
                    KeyCombination.SHIFT_DOWN);
            public void handle(KeyEvent ke) {

                if (keyComb.match(ke)) {
                    try
                    {
                        Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/OrderPane.fxml"));
                        rightPane.getChildren().add(stockPane);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        ankori.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.F,
                    KeyCombination.SHIFT_DOWN);
            public void handle(KeyEvent ke) {

                if (keyComb.match(ke)) {
                    try
                    {
                        Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/Faturat.fxml"));
                        rightPane.getChildren().add(stockPane);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        ankori.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.P,
                    KeyCombination.SHIFT_DOWN);
            public void handle(KeyEvent ke) {

                if (keyComb.match(ke)) {
                    try
                    {
                        Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/Faturat.fxml"));
                        rightPane.getChildren().add(stockPane);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        stock.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/StockPane.fxml"));
                rightPane.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }


        });

        orders.setOnMouseClicked(mouseEvent -> {
        	Pane stockPane;
			try {
				stockPane = FXMLLoader.load(getClass().getResource("/Views/OrderPane.fxml"));
				rightPane.getChildren().add(stockPane); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        });
        
        home.setOnMouseClicked(mouseEvent -> {
            try
            {
                Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/HomePane.fxml"));
                rightPane.getChildren().add(stockPane);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

        payment.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/Faturat.fxml"));
                rightPane.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }


        });

        helpBtn.setOnMouseClicked(mouseEvent -> {
            Help help=new Help();
            help.help();
        });

    }

    @FXML
    public void menu() {


        menu.setOnMouseEntered(mouseEvent -> {
            vMenu1.setVisible(true);
        });
       vMenu.setOnMouseExited(mouseEvent -> {
          vMenu1.setVisible(false);
        });

    }


}
