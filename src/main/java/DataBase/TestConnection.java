package DataBase;

import com.mysql.cj.xdevapi.Result;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
String userName="root";
String password="root";
String connectionURL="jdbc:mysql://localhost:3306/score";
Connection connection=null;
Statement statement=null;
        ResultSet result=null;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
           connection = DriverManager.getConnection(connectionURL,
                    userName, password);
            statement=connection.createStatement();
statement.executeUpdate("insert  into score.score(name,score) values ('first',50)");
            statement.executeUpdate("insert  into score.score(name,score) values ('second',100)");
            result= statement.executeQuery("select*from score.score;");
            while (result.next()){
                System.out.println(result.getInt(1));
                System.out.println(result.getString(2));
                System.out.println(result.getInt(3));
                System.out.println("-----------------------");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
