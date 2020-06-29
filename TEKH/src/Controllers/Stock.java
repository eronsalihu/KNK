package Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.print.event.PrintEvent;

public class Stock implements Initializable {

    @FXML
    private TextField searchBar;
    
    @FXML
    private Button addCart;
    
    @FXML
    private Button viewShipping;
    
    @FXML
    private Pane rightPane;
   

   
    
    @FXML private TableView<Products> tableView;
    @FXML private TableColumn<Products, ImageView> image;
    @FXML private TableColumn<Products,String> name;
    @FXML private TableColumn<Products, Double> price;
    @FXML private TableColumn<Products,String> invertory;
    @FXML private TableColumn<Products,String> categoryy;
    

    
    ObservableList<Products> productsObservableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection=Database.DBConn.setConnection();

        try{
            ResultSet resultSet=connection.createStatement().executeQuery("Select * from stock");
            while (resultSet.next()){
                productsObservableList.add(new Products(new ImageView("/Icons/"+resultSet.getInt("images")+".jpg") ,resultSet.getString("modeli"),resultSet.getDouble("cmimi"),resultSet.getInt("sasia"),resultSet.getString("lloji")));

            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        image.setCellValueFactory(new PropertyValueFactory<Products,ImageView>("Image"));
        name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
        price.setCellValueFactory(new PropertyValueFactory<Products,Double>("Price"));
        invertory.setCellValueFactory(new PropertyValueFactory<Products,String>("Invertory"));
        categoryy.setCellValueFactory(new PropertyValueFactory<Products,String>("Category"));

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

        addCart.setOnMouseClicked(mouseEvent -> {

           
        	
            try {            	
            	Products products = tableView.getSelectionModel().getSelectedItem();

                String query = "SELECT * FROM faturat ORDER BY id DESC LIMIT 1;";
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query);
                int id=0;
                if (resultSet.next()){
                    id=resultSet.getInt("id")+1;
                }

          	
	            PreparedStatement Pstatement=connection.prepareStatement("insert into products (id,name,price,category) values(?,?,?,?)");

                Pstatement.setInt(1,id);
	            Pstatement.setString(2,products.getName());
	            Pstatement.setDouble(3,products.getPrice());
	            Pstatement.setString(4,products.getCategory());
	            Pstatement.executeUpdate();



            }
	           
             catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


        });
      
        viewShipping.setOnMouseClicked(mouseEvent -> {
    	
        	Pane stockPane;
			try {
				stockPane = FXMLLoader.load(getClass().getResource("/Views/ShoppingCart.fxml"));
				rightPane.getChildren().add(stockPane); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	         
        });


    }
    
   
    
}
