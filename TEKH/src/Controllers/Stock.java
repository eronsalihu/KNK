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
import java.util.ResourceBundle;

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

    ObservableList<Products> productsObservableList= FXCollections.observableArrayList(
            new Products(new ImageView("/Icons/1.jpg"),"Iphone 11",790.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/2.jpg"),"Iphone 11 Pro",899.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/6.jpg"),"Samsung Galaxy Watch",249.00,"1000 Stock","Watch" ),
            new Products(new ImageView("/Icons/7.jpg"),"Iphone X",459.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/8.jpg"),"Samsung Galaxy Note 9 ",629.99,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/4.jpg"),"Apple Watch Series 4",339.00,"1000 Stock","Watch" ),
            new Products(new ImageView("/Icons/9.jpg"),"OnePlus & Pro",749.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/10.jpg"),"Oppo Reno 3 Pro",697.77,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/11.jpg"),"Huawei P40 Pro",799.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/5.jpg"),"Apple Watch Series 5",399.00,"1000 Stock","Watch" ),
            new Products(new ImageView("/Icons/12.jpg"),"Samsung Galaxy S20+",999.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/13.jpg"),"Samsung Galaxy Note 10",899.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/3.jpg"), "Samsung Galaxy Watch Active 2 ",245.00,"1000 Stock","Watch" ),
            new Products(new ImageView("/Icons/14.jpg"),"Xiaomi Mi 10 Pro",260.23,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/15.jpg"),"Iphone XR",479.00,"1000 Stock","Phone" ),
            new Products(new ImageView("/Icons/16.jpg"),"Iphone XS",519.00,"1000 Stock","Phone" )

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
          	  
          	  
          	 Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/tech","root","");

	            PreparedStatement Pstatement=connection.prepareStatement("insert into products (name,price,inventory,category,dateAndTime) values(?,?,?,?,?)");

	            Pstatement.setString(1,products.getName());
	            Pstatement.setDouble(2,products.getPrice());
	            Pstatement.setString(3,products.getInvertory());
	            Pstatement.setString(4,products.getCategory());
	            Pstatement.setString(5,products.thedate().toString());
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
