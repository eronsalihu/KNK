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
    private TextField username;

    @FXML
    private PasswordField confirmPwd;

    @FXML
    private PasswordField password;

    @FXML
    private Button changeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void changePassword(ActionEvent event) {

        if (username.getText().isEmpty() || password.getText().isEmpty() || confirmPwd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up");
            alert.showAndWait();
        } else {



            String query1 = "Update users set password= '" + password.getText() + "' where username='" +username.getText() + "'";
            try {
                String pass=password.getText();
                String conPass=confirmPwd.getText();

                if ((pass.equals(conPass))) {
                    Statement statement = DBConn.setConnection().createStatement();
                    statement.executeUpdate(query1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Password changed successfully!");
                    alert.showAndWait();
                    Parent singUp = FXMLLoader.load(getClass().getResource("/Views/ChangePassword.fxml"));
                    Scene singUpScene = new Scene(singUp);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(singUpScene);
                    window.close();



                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Passwords don't match");
                    alert.showAndWait();

                }


            } catch (SQLException | IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database problem");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                System.exit(0);
            }
        }
    }
}


