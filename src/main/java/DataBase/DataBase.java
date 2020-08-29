package DataBase;


import java.sql.*;

public class DataBase {
    private String userName = "root";
    private String password = "root";
    private String connectionURL = "jdbc:mysql://localhost:3306/scoreBase"; //127.0.0.1:3306 Название базы: scoreBase
    private Connection connection ;
    private Statement statement = null;
    private ResultSet result = null;
    private PreparedStatement pstmt = null;

    public DataBase() {
        createConnection(); // Сразу создаёться конект при создании объекта "DataBase"
    }

    private void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//Драйвер
            connection = DriverManager.getConnection(connectionURL, userName, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        }



    public void checkedReg(String player1,String player2) {
        boolean bool1=true;
        boolean bool2=true;
        try {
            pstmt=connection.prepareStatement("insert  into scoreBase.score(name,rating) values (?,0) ");
result= statement.executeQuery("select name from scoreBase.score ");

            while (result.next()&&(bool1||bool2)){
                if (result.getString(1).equals(player1)){ // Для нахождение пользователя №1 в БД
                    bool1=false;
                }
                if (result.getString(1).equals(player2)){  // Для нахождение пользователя №2 в БД
                    bool2=false;
                }

            }

            if (bool1){ // Если пользователь №1 не найдет добавляем нового с переданным в параметре именем
                pstmt.setString(1,player1);
                pstmt.executeUpdate();
            }
            if (bool2){ // Если пользователь №2 не найдет добавляем нового с переданным в параметре именем
               pstmt.setString(1,player2);
                pstmt.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void endGame(String winner,String loser){
        try {

           pstmt=connection.prepareStatement("select rating from scoreBase.score where name=?");
pstmt.setString(1,winner);
result=pstmt.executeQuery();
result.next();
            int scoreWinner=result.getInt(1);
if (scoreWinner==0){
    pstmt=connection.prepareStatement("update scoreBase.score set rating = 50 where name=?");
    pstmt.setString(1,winner);
    pstmt.executeUpdate();
}else {
    pstmt=connection.prepareStatement("update scoreBase.score set rating = ?+50 where name=?");
    pstmt.setInt(1,scoreWinner);
    pstmt.setString(2,winner);
    pstmt.executeUpdate();
}
            pstmt=connection.prepareStatement("select rating from scoreBase.score where name=?");
            pstmt.setString(1,loser);
            result=pstmt.executeQuery();
            result.next();
            int scoreLoser=result.getInt(1);
            if (scoreLoser>=50){
                pstmt=connection.prepareStatement("update scoreBase.score set rating = ?-50 where name=?");
                pstmt.setInt(1,scoreLoser);
                pstmt.setString(2,loser);
                pstmt.executeUpdate();
            }else if (scoreLoser>=0){
                pstmt=connection.prepareStatement("update scoreBase.score set rating = 0 where name=?");
                pstmt.setString(1,loser);
                pstmt.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement!=null){
                try {

                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (result!=null){
                try {
                    result.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if (pstmt!=null){

                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
