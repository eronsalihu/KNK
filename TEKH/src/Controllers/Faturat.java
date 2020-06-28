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
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class Faturat implements Initializable {

    @FXML private TextField searchBar;
    @FXML private TableView<FaturatHelper> tableView;
    @FXML private TableColumn<FaturatHelper, Integer> id;
    @FXML private TableColumn<FaturatHelper, String> person;
    @FXML private TableColumn<FaturatHelper, Date> time;
    @FXML private TableColumn<FaturatHelper, Double> totalCost;

        ObservableList<FaturatHelper> productsObservableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection=Database.DBConn.setConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("Select * from faturat");
            while (resultSet.next()){
                productsObservableList.add(new FaturatHelper(resultSet.getInt("id"),resultSet.getString("personi"),resultSet.getString("koha"),resultSet.getDouble("shuma")));

            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        id.setCellValueFactory(new PropertyValueFactory<FaturatHelper, Integer>("Id"));
        person.setCellValueFactory(new PropertyValueFactory<FaturatHelper, String>("Person"));
        time.setCellValueFactory(new PropertyValueFactory<FaturatHelper, Date>("Time"));
        totalCost.setCellValueFactory(new PropertyValueFactory<FaturatHelper, Double>("Cost"));

        tableView.setItems(productsObservableList);

        FilteredList<FaturatHelper> filteredData = new FilteredList<>(productsObservableList, p -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getPerson().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });



        SortedList<FaturatHelper> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);

    }

}
