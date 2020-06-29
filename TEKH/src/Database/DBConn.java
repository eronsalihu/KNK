package Database;

import com.sun.glass.ui.Window;
import javafx.scene.control.Alert;

import java.nio.charset.Charset;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class DBConn {
    public static void main(String [] args) throws Exception {
      // String a= check("Enis.Berisha","ql");
        //System.out.println(a);
       // System.out.println(getLast());

        System.out.println(changePw("qlmngdez","E.berisha1"));


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
    public static String check(String a, String b)throws Exception{
            String c=null;

            try (Connection connection = setConnection()) {
                String kerko="Select * from users where username='"+a+"' and password='"+b+"'";
                Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet rs=s.executeQuery(kerko);
                if (rs.next()==false){
                   c="asgje";

                }
                else {
                    c = rs.getString("roli");



                    }


            }




        catch( Exception ex){
            ex.printStackTrace();
        }

    return c;
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
            String query = "SELECT * FROM employees ORDER BY id DESC LIMIT 1;";
            Statement stat=connection.createStatement();
            ResultSet result=stat.executeQuery(query);
            int id = 0;
            if (result.next()){
                id=result.getInt("id");
            }
            
            
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
                String shtouser="insert into users (username,password,roli,id) values ('"+user+"','"+passwordi+"','"+c+"','"+id+"')";
                PreparedStatement prp=connection.prepareStatement(shtouser);
                prp.executeUpdate(shtouser);

            }
            System.out.println("Eshte krijuar shfrytezuesi "+a);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void signout(){
        Connection connection=setConnection();
        try{
            String iFundit=getLast();
            Date koha=new Date();
            String a=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(koha);
            String shtolog="insert into signedOut (username,kohadaljes) values ('"+iFundit+"','"+a+"')";
            PreparedStatement prp=connection.prepareStatement(shtolog);
            prp.executeUpdate(shtolog);
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
                        if (resultSet.getString("roli").equalsIgnoreCase("menaxher") || resultSet.getString("roli").equalsIgnoreCase("shites")){
                            String delete="delete from users where id='"+a+"'";

                            PreparedStatement stmt = connection.prepareStatement(delete);
                            stmt.executeUpdate(delete);

                        }

                        String emri=resultSet.getString("name");
                        String mbiemri=resultSet.getString("surname");
                        Date data_fillimit=resultSet.getDate("data_fillimit");
                        Date date=new Date();
                        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
                        String shtoEx="Insert into exEmployees (id,emri,mbiemri,data_fillimit,data_mbarimit) values ('"+a+"','"+emri+"','"+mbiemri+"','"+data_fillimit+"','"+
                                modifiedDate+"')";
                        PreparedStatement preparedStatement=connection.prepareStatement(shtoEx);
                        preparedStatement.executeUpdate(shtoEx);
                        String deleteE="delete from  employees where id='"+a+"'";
                        PreparedStatement statementa=connection.prepareStatement(deleteE);
                        statementa.executeUpdate(deleteE);

                    }


                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
    }
    public static void lastLogedIn(String name){
        Connection connection=setConnection();
        try {
            Date koha=new Date();
            String a=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(koha);
            String shtolog="insert into lastLogged (username,kohakyqjes) values ('"+name+"','"+a+"')";
            PreparedStatement prp=connection.prepareStatement(shtolog);
            prp.executeUpdate(shtolog);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static String getLast(){
        Connection connection=setConnection();
        String username=null;
        try
        {
            String query = "SELECT * FROM lastLogged ORDER BY username DESC LIMIT 1;";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            if (resultSet.next()){
                username=resultSet.getString("username");
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return username;
    }
    public static boolean changePw(String oldPw,String newPw)
    {
        boolean ndrrimi=false;
        Connection connection=setConnection();
        String useri=getLast();
        try
        {
            String query="Update users set password='"+newPw+"' where username='"+useri+"' and password='"+oldPw+"'";
            PreparedStatement prp=connection.prepareStatement(query);
            prp.executeUpdate(query);
            ndrrimi=true;

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return ndrrimi;
    }
}

