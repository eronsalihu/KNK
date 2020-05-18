package Database;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

    private static Connection dbConnection;

    public static Connection setConnection() {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            //dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","");
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tekh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Can not connect to database");
            alert.showAndWait();
            ex.printStackTrace();
            System.exit(0);
        }

        return dbConnection;
    }
}