package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Intro implements Initializable {


    @FXML
    private Button signup;

    @FXML
    private Button signin;

    @FXML
    private ImageView menu;

    @FXML
    private VBox vMenu;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void signUpHandle(ActionEvent event) throws IOException {


        Parent singUp = FXMLLoader.load(getClass().getResource("/Views/SignUp.fxml"));
        Scene singUpScene = new Scene(singUp);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(singUpScene);
        window.show();
    }
    @FXML
    public void signInHandle(ActionEvent event) throws IOException {
        Parent singUp = FXMLLoader.load(getClass().getResource("/Views/SignIn.fxml"));
        Scene singUpScene = new Scene(singUp);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(singUpScene);
        window.show();

    }

//EN/AL language
    public void menu() {
        ImageView enIcon=new ImageView("/Icons/EN.png");
        ImageView alIcon=new ImageView("/Icons/AL.png");

        enIcon.setFitWidth(30);
        enIcon.setFitHeight(30);
        alIcon.setFitWidth(30);
        alIcon.setFitHeight(30);

        vMenu.getChildren().addAll(enIcon,alIcon);



        menu.setOnMouseEntered(mouseEvent -> {
            enIcon.setVisible(true);
            alIcon.setVisible(true);
            });

        vMenu.setOnMouseExited(mouseEvent -> {
            enIcon.setVisible(false);
            alIcon.setVisible(false);
        });

        /*enIcon.setOnMouseClicked(mouseEvent -> {
            I18N.setLocale(new Locale("EN"));
        });

        alIcon.setOnMouseClicked(mouseEvent -> {
            I18N.setLocale(new Locale("AL"));
        });*/

    }

}
