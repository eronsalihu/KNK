package Database;

import com.sun.glass.ui.Window;
import javafx.scene.control.Alert;

import java.nio.charset.Charset;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DBConn {
    public static void main(String [] args) {
        //check("enis.1","Berisha1");
       // System.out.println("Roli i Enisit eshte: ");





        shto("Eron","Salihu","shites");
        //delete(1);
    }
    
  

    public static Connection setConnection() {
        String url="jdbc:mysql://localhost:3306/tech";
        String name="root";
        String password="E.berisha1";
        Connection  dbConnection = null;
        try {

              dbConnection = DriverManager.getConnection(url,name,password);


        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Can not connect to database");
            alert.showAndWait();
            ex.printStackTrace();
  //          System.exit(0);
        }

        return dbConnection;
    }
    public static void check(String a, String b){
            String c=null;
            try (Connection connection = setConnection()) {
                String kerko="Select * from users where username='"+a+"' and password='"+b+"'";
                Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet rs=s.executeQuery(kerko);
                if (rs.next()==false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Your username or password is wrong");
                    alert.showAndWait();

                }
                else {
                    while (rs.next()){
                        c = rs.getString(1);




                    }
                }

            }

        catch( Exception ex){
            ex.printStackTrace();
        }

    }
    public static void shto(String a, String b, String c){
        Connection connection=setConnection();
        try{
            int count=0;
            String user="";
            Date date=new Date();
            String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
            String shto="insert into employees(name,surname,roli,data_fillimit) values ('"+a+"','"+b+"','"+c+"','"+modifiedDate+"')";
            PreparedStatement preparedStatement=connection.prepareStatement(shto);
            preparedStatement.executeUpdate(shto);
            ResultSet resultSet=preparedStatement.getResultSet();
            if(c.equalsIgnoreCase("shites") ||c.equalsIgnoreCase("menaxher")){
                String username=a+"."+b;
                String checkusername="Select count(*) from employees where name='"+a+"' and surname='"+b+"'";
                Statement statement=connection.createStatement();
                ResultSet resultSet1=statement.executeQuery(checkusername);
                if(resultSet1.next()==false){
                     user=username;
                }
                else {

                    count= resultSet1.getInt("count(*)");
                    int prapashtesa=count+1;
                    String numri=String.valueOf(prapashtesa);
                    user=username+numri;

                }
                //generate a random password
                int leftLimit = 97; // letter 'a'
                int rightLimit = 122; // letter 'z'
                int targetStringLength = 8;
                Random random = new Random();
                StringBuilder buffer = new StringBuilder(targetStringLength);
                for (int i = 0; i < targetStringLength; i++) {
                    int randomLimitedInt = leftLimit + (int)
                            (random.nextFloat() * (rightLimit - leftLimit + 1));
                    buffer.append((char) randomLimitedInt);
                }
                String passwordi = buffer.toString();
                String shtouser="insert into users (username,password,roli) values ('"+user+"','"+passwordi+"','"+c+"')";
                PreparedStatement prp=connection.prepareStatement(shtouser);
                prp.executeUpdate(shtouser);

            }
            System.out.println("Eshte krijuar shfrytezuesi "+a);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void delete(int a){
        Connection connection=setConnection();
                try{
                    String merri="Select * from employees where id='"+a+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(merri);
                    while (resultSet.next()){

                        String emri=resultSet.getString("name");
                        String mbiemri=resultSet.getString("surname");
                        Date data_fillimit=resultSet.getDate("data_fillimit");
                        Date date=new Date();
                        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
                        String shtoEx="Insert into exEmployees (id,emri,mbiemri,data_fillimit,data_mbarimit) values ('"+a+"','"+emri+"','"+mbiemri+"','"+data_fillimit+"','"+
                                modifiedDate+"')";
                        PreparedStatement preparedStatement=connection.prepareStatement(shtoEx);
                        preparedStatement.executeUpdate(shtoEx);

                    }

                    String delete="delete from users where id='"+a+"'";
                    String deleteE="delete from  employees where id='"+a+"'";
                    PreparedStatement stmt = connection.prepareStatement(delete);
                    stmt.executeUpdate(delete);
                    PreparedStatement statementa=connection.prepareStatement(deleteE);
                    statementa.executeUpdate(deleteE);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
    }
}