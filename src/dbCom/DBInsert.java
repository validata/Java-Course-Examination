package dbCom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.math.*;

public class DBInsert {
    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        String url = "jdbc:sqlite:TheDB.db";
        //String username = "root";
        //String password = "root";

        Connection conn = null;
        try {
            Driver myDriver2 = new org.sqlite.JDBC();
            DriverManager.registerDriver(myDriver2);
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to DB established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String insertCourse(String courseName) {
        //connect();
        String sql = "INSERT INTO Course(name) VALUES(?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courseName;
    }




        public void insertUser(String name, String email, String password, Boolean isTeacher) {
        connect();
        String sql = "INSERT INTO users(name, email, password, isTeacher) VALUES(?,?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setBoolean(4, isTeacher);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ConnectCourseWithUser(Integer userId, Integer courseId) {
        String sql = "INSERT INTO userCourses(userId, courseId VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DBInsert app = new DBInsert();

        // insert new rows

        //app.insertUser("apa", "apa", "apa", true);
        //app.ConnectCourseWithUser(1,8);
    }
}
