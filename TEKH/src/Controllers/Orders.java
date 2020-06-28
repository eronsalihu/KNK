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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    @FXML private TableColumn<OrderModel,Integer> id;
    @FXML private TableColumn<OrderModel,String> sent;

    private ObservableList<OrderModel> orders = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection connection=Database.DBConn.setConnection();
            ResultSet resultSet=connection.createStatement().executeQuery("Select * from orders");
            while (resultSet.next()){
                int a=resultSet.getInt("id");
                ResultSet rs=connection.createStatement().executeQuery("select id from faturat");
                while (rs.next()){
                    if (a==rs.getInt("id")){
                        String query="Update orders set aprovuar='Sent' where id='"+a+"'";
                        PreparedStatement prp=connection.prepareStatement(query);
                        prp.executeUpdate(query);
                    }
                    else{
                        String query="Update orders set aprovuar='Not Sent' where id='"+a+"'";
                        PreparedStatement prp=connection.prepareStatement(query);
                        prp.executeUpdate(query);
                    }
                }
                orders.add(new OrderModel(resultSet.getString("name"),resultSet.getDate("koha"),resultSet.getDouble("price"),resultSet.getDouble("VAT"),resultSet.getDouble("Total"),resultSet.getInt("id"),resultSet.getString("aprovuar")));


            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        orderName.setCellValueFactory(new PropertyValueFactory<OrderModel,String>("Name"));
        orderDate.setCellValueFactory(new PropertyValueFactory<OrderModel,Date>("Date"));
        orderPrice.setCellValueFactory(new PropertyValueFactory<OrderModel,Double>("Price"));
        orderVAT.setCellValueFactory(new PropertyValueFactory<OrderModel,Double>("VAT"));
        orderTotal.setCellValueFactory(new PropertyValueFactory<OrderModel, Double>("Total"));
        id.setCellValueFactory(new PropertyValueFactory<OrderModel,Integer>("id"));
        sent.setCellValueFactory(new PropertyValueFactory<OrderModel,String>("sent"));
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
