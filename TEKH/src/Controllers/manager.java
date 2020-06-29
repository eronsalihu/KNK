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

public class manager implements Initializable {

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
    private Button fshipuntor;

    @FXML
    private Button stock;

    @FXML
    private Button orders;

    @FXML
    private Button payment;


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

    @FXML
    private Button shtoPuntor;


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

        stock.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/StockPane.fxml"));
                rightPane.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }


        });
        shtoPuntor.setOnMouseClicked(mouseEvent -> {
            try{
                Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/ViewWorkersPane.fxml"));
                rightPane.getChildren().add(stockPane);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        fshipuntor.setOnMouseClicked(mouseEvent -> {
            try
            {
                Pane stockPane=FXMLLoader.load(getClass().getResource("/Views/deleteWorkerPane.fxml"));
                rightPane.getChildren().add(stockPane);}
            catch (Exception ex){
                ex.printStackTrace();
            }
        });


        orders.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/OrderPane.fxml"));
                rightPane.getChildren().add(stockPane);
            } catch (IOException e) {
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
