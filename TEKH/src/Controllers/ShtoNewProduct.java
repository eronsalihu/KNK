package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ShtoNewProduct implements Initializable {
    @FXML
    private TextField image;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TextField model;
    @FXML
    private TextField sasia;
    @FXML
    private TextField cmimi;
    @FXML
    private Button shtoBtn;

    ObservableList<String> list= FXCollections.observableArrayList("Phone","Tablet","Smart Watch","Laptop","Headphone","Monitor","Aksesor");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo.setItems(list);
    }
    public void shtoProdukt(){
        try {
            if (image.getText().isEmpty()|| combo.getValue().isEmpty()|| model.getText().isEmpty()|| sasia.getText().isEmpty()|| cmimi.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill up");
                alert.showAndWait();
            }
            else{

            int sasi= Integer.parseInt(sasia.getText());
            double cmimet= Double.parseDouble(cmimi.getText());
            Database.DBConn.shtoNewProdukt(image.getText(),combo.getValue(),model.getText(),sasi,cmimet);
            Alert confirm1=new Alert(Alert.AlertType.INFORMATION);
            confirm1.setContentText("Eshte shtuar produkti i ri me sukses");
            confirm1.showAndWait();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
