package Controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesUI implements Initializable {


    @FXML
    private StackPane stackPane;

    @FXML
    private Pane rightPane;

    @FXML
    private Pane rightPane1;

    @FXML
    private Button stock;

    @FXML
    private Button orders;

    @FXML
    private Button payment;

    @FXML
    private VBox vMenu;

    @FXML
    private ImageView menu;

    @FXML
    private VBox vMenu1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vMenu1.setVisible(false);

        rightPane.translateXProperty().set(-1 * stackPane.getWidth());
        var keyValue = new KeyValue(rightPane.translateXProperty(), 0, Interpolator.EASE_IN);
        var keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
        var timeline = new Timeline(keyFrame);
        timeline.setOnFinished(evt -> {
            stackPane.getChildren().remove(rightPane1);
        });
        timeline.play();

        stock.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/StockPane.fxml"));
                rightPane.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }


        });

        orders.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/OrderPane.fxml"));
                rightPane.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }


        });

        payment.setOnMouseClicked(mouseEvent -> {
            try {
                Pane stockPane= FXMLLoader.load(getClass().getResource("/Views/BillPane.fxml"));
                rightPane.getChildren().add(stockPane);
            } catch (IOException e) {
                e.printStackTrace();

            }


        });

    }
    @FXML
    public void menu() {


        menu.setOnMouseEntered(mouseEvent -> {
            vMenu1.setVisible(true);
        });
        vMenu.setOnMouseExited(mouseEvent -> {
            vMenu1.setVisible(false);
        });

    }
}
