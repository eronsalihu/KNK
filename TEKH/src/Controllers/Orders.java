package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;

public class Orders implements Initializable {

    @FXML private TextField searchBar;

    @FXML private TableView<OrderModel> orderTable;
    @FXML private TableColumn<OrderModel, String> orderName;
    @FXML private TableColumn<OrderModel, Date> orderDate;
    @FXML private TableColumn<OrderModel, Double> orderPrice;
    @FXML private TableColumn<OrderModel, Double> orderVAT;
    @FXML private TableColumn<OrderModel, Double> orderTotal;

    private ObservableList<OrderModel> orders = FXCollections.observableArrayList(
      new  OrderModel("Order#001", new Date(2020, 06, 22),120.00,18,141.6 ),
            new  OrderModel("Order#002", new Date(2020, 06, 23), 150.00, 18, 177 ),
            new  OrderModel("Order#003", new Date(2020, 06, 24),220.00,18,259.6 ),
            new  OrderModel("Order#004", new Date(2020, 06, 28),600.00,18,708 )
    );

    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderName.setCellValueFactory(new PropertyValueFactory<OrderModel,String>("Name"));
        orderDate.setCellValueFactory(new PropertyValueFactory<OrderModel,Date>("Date"));
        orderPrice.setCellValueFactory(new PropertyValueFactory<OrderModel,Double>("Price"));
        orderVAT.setCellValueFactory(new PropertyValueFactory<OrderModel,Double>("VAT"));
        orderTotal.setCellValueFactory(new PropertyValueFactory<OrderModel,Double>("Total"));
        orderTable.setItems(orders);


        FilteredList<OrderModel> filteredData = new FilteredList<>(orders, p -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(x -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (x.getName().toLowerCase().contains(lowerCaseFilter) ||
                        x.getDate().toString().contains(lowerCaseFilter))
                {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });



        SortedList<OrderModel> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(orderTable.comparatorProperty());

        orderTable.setItems(sortedData);
    };

}
