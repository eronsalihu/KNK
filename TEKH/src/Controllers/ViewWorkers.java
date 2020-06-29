package Controllers;

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
import java.util.Date;
import java.util.ResourceBundle;

public class ViewWorkers implements Initializable {
    @FXML
    private TextField searchBar;
    @FXML
    private TableView<WorkersTable> table;
    @FXML
    private TableColumn<WorkersTable, Integer> id_col;
    @FXML
    private TableColumn<WorkersTable,String> name_col;
    @FXML
    private TableColumn<WorkersTable,String> surname_col;
    @FXML
    private TableColumn<WorkersTable,String> roli_col;
    @FXML
    private TableColumn<WorkersTable, Date> date_col;
    @FXML
            private Button addBtn;

    ObservableList<WorkersTable> observableList= FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setOnMouseClicked(mouseEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/SignUp.fxml"));
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
            ResultSet resultSet=connection.createStatement().executeQuery("Select * from employees");
            while (resultSet.next()){
                observableList.add(new WorkersTable(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("surname"),resultSet.getString("roli"),resultSet.getDate("data_fillimit")));


            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname_col.setCellValueFactory(new PropertyValueFactory<>("surname"));
        roli_col.setCellValueFactory(new PropertyValueFactory<>("roli"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("data"));
        table.setItems(observableList);

        FilteredList<WorkersTable> filteredData = new FilteredList<>(observableList, p -> true);
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



        SortedList<WorkersTable> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
}
