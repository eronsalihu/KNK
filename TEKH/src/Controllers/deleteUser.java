package Controllers;

import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class deleteUser implements Initializable {
   @FXML
   private TextField searchBar;
    @FXML
    private TableView<deleteTable> table;
    @FXML
    private TableColumn<deleteTable, Integer> id_col;
    @FXML
    private TableColumn<deleteTable,String> name_col;
    @FXML
    private TableColumn<deleteTable,String> surname_col;
    @FXML
    private TableColumn<deleteTable,String> started_col;
    @FXML
    private TableColumn<deleteTable,String> ended_col;
    @FXML
            private Button DeleteWorker;

    ObservableList<deleteTable> observableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DeleteWorker.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/deleteWorker.fxml"));
            Parent deleteU = null;
            try {
                deleteU = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image applicationIcon = new Image(getClass().getResourceAsStream("/Icons/icon.png"));
            Stage stage = new Stage();
            stage.setTitle("TEKH");
            stage.getIcons().add(applicationIcon);
            stage.setScene(new Scene(deleteU));
            stage.show();

        });
        try{
        Connection connection=Database.DBConn.setConnection();
        ResultSet resultSet=connection.createStatement().executeQuery("Select * from exEmployees");
        while (resultSet.next()){
            observableList.add(new deleteTable(resultSet.getInt("id"),resultSet.getString("emri"),resultSet.getString("mbiemri"),resultSet.getString("data_fillimit"),resultSet.getString("data_mbarimit")));

        }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname_col.setCellValueFactory(new PropertyValueFactory<>("surname"));
        started_col.setCellValueFactory(new PropertyValueFactory<>("dataFillimit"));
        ended_col.setCellValueFactory(new PropertyValueFactory<>("dataMbarimit"));
        table.setItems(observableList);

        FilteredList<deleteTable> filteredData = new FilteredList<>(observableList, p -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });



        SortedList<deleteTable> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
}
