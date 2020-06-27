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
import java.util.Optional;
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
            try {



                String usernameText= username.getText();
                String passwordText=password.getText();
                String Roli= Database.DBConn.check(usernameText,passwordText);


                if (Roli.equalsIgnoreCase("menaxher")) {

                    Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Kujdes.Zgjidhni Vazhdimesin");
                    confirmation.setHeaderText("Ne varesi te rolit shfaqet dritjarja perkatese");
                    confirmation.setContentText("Zgjidhni rolin qe deshironi");

                    ButtonType menaxheri = new ButtonType("Menaxher");
                    ButtonType shitesi = new ButtonType("Shites");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    confirmation.getButtonTypes().setAll(menaxheri, shitesi, buttonTypeCancel);
                    Optional<ButtonType> result = confirmation.showAndWait();
                    if (result.get()==menaxheri){
                        sigIn.setOnAction(event -> {
                            try {
                                HomeHandle(event);
                                Database.DBConn.lastLogedIn(usernameText);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                    }
                    if (result.get()==shitesi){
                        sigIn.setOnAction(event -> {
                            try {
                                HomeHandle(event);
                                Database.DBConn.lastLogedIn(usernameText);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                    }

                } else if (Roli.equalsIgnoreCase("asgje")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login result");
                    alert.setHeaderText(null);
                    alert.setContentText("Email or password is wrong!");
                    alert.showAndWait();

                }
                else if (Roli.equalsIgnoreCase("shites")){
                    sigIn.setOnAction(event -> {
                        try {
                            HomeHandle(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }

            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database problem");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
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

        Parent signUp = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
        Scene singUpScene = new Scene(signUp);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
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
