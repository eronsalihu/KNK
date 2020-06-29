package Controllers;

import Database.DBConn;
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
import javafx.scene.text.Text;
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
	private Text textfield;

	@FXML
	private Text productText;

	@FXML
	private Text warranty;

	@FXML
	private Button goBack;

	@FXML
	private Button deleteRow;

	@FXML
	private Button garancioni;

	@FXML
	private Pane rightPane;
	@FXML
	private Button done;

	@FXML private TableView<Products> tableView;

	@FXML public TableColumn<Products,String> dateAndTime;
	@FXML private TableColumn<Products,String> name;
	@FXML private TableColumn<Products, Double> price;
	@FXML private TableColumn<Products,String> categoryy;
	@FXML public TableColumn<Products, Integer> id;



	ObservableList<Products> productsObservableList=  FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		if(isWeekend(LocalDate.now()))
		{
			textfield.setVisible(true);
		}
		else {
			textfield.setVisible(false);
		}
		if(isWeekend(LocalDate.now()))
		{
			productText.setVisible(false);
		}
		else {
			productText.setVisible(true);
		}

		Connection connection;
		connection=Database.DBConn.setConnection();
		try {
			Statement st = connection.createStatement();
			String sql = ("SELECT * FROM products ;");
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				if(isWeekend(LocalDate.now()))
				{
					productsObservableList.add(new Products(rs.getInt("id"),rs.getString("name"),rs.getDouble("price")-rs.getDouble("price")*0.2,rs.getString("category")));

				}
				else {
					productsObservableList.add(new Products(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"),rs.getString("category")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id.setCellValueFactory(new PropertyValueFactory<Products,Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
		price.setCellValueFactory(new PropertyValueFactory<Products,Double>("Price"));
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

		done.setOnMouseClicked(mouseEvent -> {
			try {


			String query="Select * from products order by id  desc limit 1;";
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(query);
			int maxid=0;
			if (resultSet.next()){
				maxid=resultSet.getInt("id");
			}
			String query1="Select * from products where id='"+maxid+"'";
			Statement stat=connection.createStatement();
			ResultSet rs=stat.executeQuery(query1);
			double cmimi=0;
			while (rs.next()){
				cmimi+=rs.getDouble("price");
			}
			DBConn.insertFature(maxid,cmimi);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		});

		deleteRow.setOnMouseClicked(mouseEvent -> {
			Products products = tableView.getSelectionModel().getSelectedItem();
			try {
				Statement st = connection.createStatement();

				String sql = ("SELECT * FROM products;");
				ResultSet rs = st.executeQuery(sql);
				for(int i=0;i<30;i++)
				{
					if(rs.next())
					{
						String str1 = rs.getString("dateAndTime");
						if(str1.equals(products.getDateAndTime().toString()))
						{
							String query = "delete from products where dateAndTime= ?";
							PreparedStatement preparedStmt = connection.prepareStatement(query);
							preparedStmt.setString(1,str1);

							preparedStmt.execute();

							Pane stockPane;
							try {
								stockPane = FXMLLoader.load(getClass().getResource("/Views/ShoppingCart.fxml"));
								rightPane.getChildren().add(stockPane);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}
					else {
						System.out.println();

					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}




		});

	}

	public static boolean isWeekend(LocalDate date) {
		DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
		switch (dayOfWeek) {
			case SATURDAY:
			case SUNDAY:
				return true;
			default:
				return false;
		}
	}
}
