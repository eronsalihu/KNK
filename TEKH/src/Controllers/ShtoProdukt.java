package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ShtoProdukt implements Initializable {
    @FXML
    private TextField produkti;

    @FXML
    private TextField sasia;
    @FXML
    private Button shtoBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void ndreqi(){
        if (produkti.getText().isEmpty()|| sasia.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up");
            alert.showAndWait();
        }
        else {
            String produk=produkti.getText();
            int sasi= Integer.parseInt(sasia.getText());
            Database.DBConn.shtosasi(produk,sasi);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sasia e produktit u shtua me sukses");
            alert.showAndWait();

        }
    }
}
