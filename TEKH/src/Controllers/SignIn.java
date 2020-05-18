package Controllers;

import Database.DBConn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignIn implements Initializable {


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button sigIn;

    @FXML
    private VBox vMenu;

    @FXML
    private ImageView menu;

    @FXML
    private Label forgotPwd;


    @FXML
    private void SignIn() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up");
            alert.showAndWait();
        } else {
            String query = "Select * from users where username = ? OR email = ? AND password = ?";
            try {

                PreparedStatement preparedStatement = DBConn.setConnection().prepareStatement(query);

                preparedStatement.setString(1, username.getText());
                preparedStatement.setString(2, username.getText());
                preparedStatement.setString(3, password.getText());

                ResultSet result = preparedStatement.executeQuery();

                if (result.next()) {
                   sigIn.setOnAction(event -> {
                       try {
                           HomeHandle(event);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   });
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login result");
                    alert.setHeaderText(null);
                    alert.setContentText("Email or password is wrong!");
                    alert.showAndWait();

                }

            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database problem");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();
                System.exit(0);
            }

        }

    }
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


    }

    @FXML
    public void HomeHandle(ActionEvent event) throws IOException {

        Parent singUp = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
        Scene singUpScene = new Scene(singUp);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(singUpScene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forgotPwd.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ChangePassword.fxml"));
            Parent changePwd = null;
            try {
                changePwd = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            Image applicationIcon = new Image(getClass().getResourceAsStream("/Icons/icon.png"));
            stage.setTitle("TEKH");
            stage.getIcons().add(applicationIcon);
            stage.setScene(new Scene(changePwd));
            stage.show();

        });


    }
}
