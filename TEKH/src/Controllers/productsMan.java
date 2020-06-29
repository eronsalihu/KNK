package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class productsMan implements Initializable {
    @FXML
    private Button newprod;
    @FXML
    private Button existing;
    @FXML
    private Pane pani;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newprod.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/shtoNewProduct.fxml"));
                pani.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }

        });
        existing.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/shtoprodukt.fxml"));
                pani.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }

        });

    }
}
