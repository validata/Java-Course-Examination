package dbCom;

import java.sql.*;
import java.util.ArrayList;

public class DBFetch {
    private Connection connect() {
        String url = "jdbc:sqlite:TheDB.db";
        Connection conn = null;
        try
        {   Driver myDriver2 = new org.sqlite.JDBC();
            DriverManager.registerDriver(myDriver2);
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Boolean checkLogin(String email, String password) throws SQLException {
// TODO MODIFIERA DENNA KOD:
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;


        Boolean checkLoginAnswer;
        String sql = "SELECT * FROM Student WHERE email = ? AND password = ?;";
        String sql2 = "SELECT * FROM Teacher WHERE email = ? AND password = ?;";
        try
        {   Connection conn = this.connect();
            preparedStatement1 = conn.prepareStatement(sql);
            preparedStatement1.setString(1,email);
            preparedStatement1.setString(2,password);

            preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setString(1,email);
            preparedStatement2.setString(2,password);

            resultSet2 = preparedStatement2.executeQuery();
            resultSet1 = preparedStatement1.executeQuery();
            if (resultSet1.next() || resultSet2.next() ) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            preparedStatement1.close();
            preparedStatement2.close();
            resultSet1.close();
            resultSet2.close();
        }
    }
}