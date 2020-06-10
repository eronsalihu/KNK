package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @FXML
    public void onLoginButtonClick(javafx.event.ActionEvent actionEvent) {
        String userName=usernameField.getText();
        String password=passwordField.getText();

        if (userName.length()==0 || password.length()==0){
            Alert fail= new Alert(Alert.AlertType.WARNING);
            fail.setHeaderText("failure");
            fail.setContentText("Your username or password is blank!");
            fail.showAndWait();
        }
        else if(userName.equalsIgnoreCase("enis")){
            Alert menagjeri=new Alert(Alert.AlertType.CONFIRMATION);
            menagjeri.setTitle("Manages");
            menagjeri.setHeaderText("A deshironi te vazhdoni ne rolin e menagjerit?");

            Optional<ButtonType> result = menagjeri.showAndWait();
            if(result.get() == ButtonType.OK)
            System.out.println("Yes clicked");
            else if(result.get() == ButtonType.CANCEL)
                System.out.println("Cancel Clicked");
        }
    }
}
