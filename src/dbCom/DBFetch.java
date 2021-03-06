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

    public Boolean tryLogin(String email, String password, Boolean isTeacher) {
// TODO MODIFIERA DENNA KOD:
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
        Connection conn1 = this.connect();

        try {
            if (!isTeacher) {
                String sql = "SELECT * FROM Student WHERE email = ? AND password = ?;";
                preparedStatement1 = conn1.prepareStatement(sql);
                preparedStatement1.setString(1, email);
                preparedStatement1.setString(2, password);
                resultSet1 = preparedStatement1.executeQuery();
                try {
                    if (resultSet1.next()) {
                        return true;
                    }
                } catch (Exception e) {
                    System.out.println("Exception!");
                    return false;
                } finally {
                    preparedStatement1.close();
                    resultSet1.close();
                }
                return false;
            } else {
                String sql = "SELECT * FROM Teacher WHERE email = ? AND password = ?;";
                preparedStatement1 = conn1.prepareStatement(sql);
                preparedStatement1.setString(1, email);
                preparedStatement1.setString(2, password);
                resultSet1 = preparedStatement1.executeQuery();
                } try {
                    if (resultSet1.next()) {
                        return true;
                    }
            } catch (Exception e) {
                System.out.println("Exception E !");
                resultSet1.close();
                preparedStatement1.close();
            } finally {
                preparedStatement1.close();
                resultSet1.close();
            }

        } catch (Exception e) {
            System.out.println("Exception e! DBFetch couldnt connect. Msg:");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}