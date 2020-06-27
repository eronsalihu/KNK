package Controllers;

import Database.DBConn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class ChangePassword extends Component implements Initializable {

    @FXML
    private  TextField username;

    @FXML
    private  PasswordField confirmPwd;

    @FXML
    private PasswordField newPwd;

    @FXML
    private Button changeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private void changePassword(ActionEvent event) {

        if ( username.getText().isEmpty()|| newPwd.getText().isEmpty() || confirmPwd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up");
            alert.showAndWait();
        } else {




                String pass=newPwd.getText();
                String conPass=confirmPwd.getText();

                if ((pass.equals(conPass))) {

                if(  Database.DBConn.changePw(username.getText(),pass))
                  try {
                      Alert confirm=new Alert(Alert.AlertType.INFORMATION);
                      confirm.setContentText("Passwordi juaj u nderrua me sukses");
                      confirm.showAndWait();
                      Parent singUp = FXMLLoader.load(getClass().getResource("/Views/ChangePassword.fxml"));
                      Scene singUpScene = new Scene(singUp);

                      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      window.setResizable(false);
                      window.setScene(singUpScene);
                      window.close();
                  }
                  catch (Exception ex){
                      ex.printStackTrace();
                  }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Passwords don't match");
                    alert.showAndWait();

                }



        }
    }
}


