package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ResourceBundle;

public class Shopping implements Initializable {

    @FXML
    private TextField searchBar;
    
    @FXML
    private Button goBack;
    
    @FXML
    private Button deleteRow;
    
    @FXML
    private Pane rightPane;
   
    @FXML private TableView<Products> tableView;
    
    @FXML public TableColumn<Products,String> dateAndTime;
    @FXML private TableColumn<Products,String> name;
    @FXML private TableColumn<Products, Double> price;
    @FXML private TableColumn<Products,String> invertory;
    @FXML private TableColumn<Products,String> categoryy;
    
  
    
    ObservableList<Products> productsObservableList=  FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	 }
