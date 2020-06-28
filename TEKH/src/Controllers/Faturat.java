
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

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Faturat implements Initializable {

    @FXML private TextField searchBar;
    @FXML private TableView<FaturatHelper> tableView;
    @FXML private TableColumn<FaturatHelper, Integer> id;
    @FXML private TableColumn<FaturatHelper, String> person;
    @FXML private TableColumn<FaturatHelper, Date> time;
    @FXML private TableColumn<FaturatHelper, Double> totalCost;

        ObservableList<FaturatHelper> productsObservableList= FXCollections.observableArrayList(
            new FaturatHelper(1,"Shitesi1", new Date(2020, 6, 22, 10,30,11),790.0),
            new FaturatHelper(2,"Shitesi1", new Date(2020, 6, 22, 12,34,12),899.0),
            new FaturatHelper(3,"Shitesi1", new Date(2020, 6, 22, 12,50,22),249.0),
            new FaturatHelper(4,"Shitesi2", new Date(2020, 6, 23, 14,31,34),459.0),
            new FaturatHelper(5,"Shitesi2", new Date(2020, 6, 23, 16,13,55),629.9),
            new FaturatHelper(6,"Shitesi2", new Date(2020, 6, 23, 17,22,43),339.0),
            new FaturatHelper(7,"Shitesi2", new Date(2020, 6, 23, 20,33,33),749.0)
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<FaturatHelper, Integer>("Id"));
        person.setCellValueFactory(new PropertyValueFactory<FaturatHelper, String>("Person"));
        time.setCellValueFactory(new PropertyValueFactory<FaturatHelper, Date>("Time"));
        totalCost.setCellValueFactory(new PropertyValueFactory<FaturatHelper, Double>("Cost"));

        tableView.setItems(productsObservableList);


    }

}
