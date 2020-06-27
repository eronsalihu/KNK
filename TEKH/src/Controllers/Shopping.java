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
    	 
    	 
    	Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tech","root","");
			  Statement st = connection.createStatement();
		        String sql = ("SELECT * FROM products;");
		        ResultSet rs = st.executeQuery(sql);
		        while(rs.next())
		        {
		        	if(isWeekend(LocalDate.now()))
		        	{
			        	productsObservableList.add(new Products(rs.getString("name"),rs.getDouble("price")-rs.getDouble("price")*0.2,rs.getString("inventory"),rs.getString("category"),rs.getString("dateAndTime")));

		        	}
		        	else {
			        	productsObservableList.add(new Products(rs.getString("name"),rs.getDouble("price"),rs.getString("inventory"),rs.getString("category"),rs.getString("dateAndTime")));
					}
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
        name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
        price.setCellValueFactory(new PropertyValueFactory<Products,Double>("Price"));
        invertory.setCellValueFactory(new PropertyValueFactory<Products,String>("Invertory"));
        categoryy.setCellValueFactory(new PropertyValueFactory<Products,String>("Category"));
        dateAndTime.setCellValueFactory(new PropertyValueFactory<Products,String>("dateAndTime"));

        tableView.setItems(productsObservableList);

        FilteredList<Products> filteredData = new FilteredList<>(productsObservableList, p -> true);

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

	    
        SortedList<Products> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
       
        goBack.setOnMouseClicked(mouseEvent -> {
        	
        	Pane stockPane;
			try {
				stockPane = FXMLLoader.load(getClass().getResource("/Views/StockPane.fxml"));
				rightPane.getChildren().add(stockPane); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	         
        });
      
    
