package dbCom;

import java.sql.*;

public class DBUpdate {
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

    public Boolean updateCourseTeacher(String name, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = this.connect();

        try {
            String sql2 = "INSERT INTO Course (name, teacher) VALUES(?,?)";
            String sql = "UPDATE Course SET teacher = ? WHERE name = ?";
            preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: ");
            System.out.println(e);
        }
        return true;
    }
}
