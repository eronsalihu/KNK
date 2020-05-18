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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    public TextField firstname;

    @FXML
    public TextField lastname;

    @FXML
    public TextField username;

    @FXML
    public TextField email;

    @FXML
    public PasswordField password;

    @FXML
    private Button signupBtn;

    @FXML
    private Label backToSingIn;

    @FXML
    private VBox vMenu;

    @FXML
    private ImageView menu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @FXML
    private void SignUp(ActionEvent event) {
        if (firstname.getText().isEmpty() || lastname.getText().isEmpty() || username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up");
            alert.showAndWait();
        } else {


            String query1 = "Insert into users values ('" + firstname.getText() + "','" + lastname.getText() +
                    "','" + username.getText() + "','" + email.getText() + "','" + password.getText() + "')";
            try {

                PreparedStatement st = DBConn.setConnection().prepareStatement("select * from users where username = ?");
                st.setString(1, username.getText());
                ResultSet r1 = st.executeQuery();
                if (r1.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Username Already exists");
                    alert.showAndWait();

                } else {
                    Statement statement = DBConn.setConnection().createStatement();
                    statement.executeUpdate(query1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("You signed up!");
                    alert.showAndWait();
                    Parent singUp = FXMLLoader.load(getClass().getResource("/Views/SignIn.fxml"));
                    Scene singUpScene = new Scene(singUp);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(singUpScene);
                    window.show();

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

    @FXML
    private void setBackToSingIn()  {
        backToSingIn.setOnMouseClicked(mouseEvent -> {
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
}

