package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class deleteWorker implements Initializable {
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField ID;
    @FXML
    private TextField nameSurname;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void deleteUser(ActionEvent event){
        if ( ID.getText().isEmpty()|| nameSurname.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up");
            alert.showAndWait();
        } else {
            int a= Integer.parseInt(ID.getText());
            try{
                Database.DBConn.delete(a);
                Alert confirm=new Alert(Alert.AlertType.INFORMATION);
                confirm.setContentText("Punetori u fshi");
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

        }
    }


}
