package Controllers;

import Database.DBConn;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.*;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public ComboBox<String> combo;

    @FXML
    public Button shtoBtn;

    ObservableList<String> list= FXCollections.observableArrayList("Menaxher","Shites","Teknik","Pastrues","Narrator");




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    combo.setItems(list);

    }

    @FXML
    private void SignUp(ActionEvent event) throws IOException {
        String value=combo.getValue();
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() ||  value.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up");
            alert.showAndWait();
        } else {
            String emri=firstName.getText();
            String mbiemri=lastName.getText();
            Database.DBConn.shto(emri,mbiemri,value);
            if (value.equalsIgnoreCase("shites") || value.equalsIgnoreCase("menaxher")){
                Connection connection=Database.DBConn.setConnection();
                int id = 0;
                try
                {
                    String query = "SELECT * FROM employees ORDER BY id DESC LIMIT 1;";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);
                    if (resultSet.next()){
                        id=resultSet.getInt("id");
                    }
                    String query1="Select * from users where id='"+id+"'";
                    Statement stat=connection.createStatement();
                    ResultSet rs=stat.executeQuery(query1);
                    String username="";
                    String password="";

                    while (rs.next()){
                        username=rs.getString("username");
                        password=rs.getString("password");
                    }

                    Alert confirm1=new Alert(Alert.AlertType.INFORMATION);
                    confirm1.setContentText("Username i userit te ri eshte  "+username+" dhe passwordi "+ password);
                    confirm1.showAndWait();


                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            Alert confirm=new Alert(Alert.AlertType.INFORMATION);
            confirm.setContentText("Punetori ri u shtua");
            confirm.showAndWait();
            Parent singUp = FXMLLoader.load(getClass().getResource("/Views/SignUp.fxml"));
            Scene singUpScene = new Scene(singUp);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(singUpScene);
            window.close();

        }
    }
}

