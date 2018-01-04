package dbCom;

import model.Student;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.math.*;

public class DBInsert {
    // @return the Connection object
    private Connection connect() {
        String url = "jdbc:sqlite:TheDB.db";

        Connection conn = null;
        try {
            Driver myDriver2 = new org.sqlite.JDBC();
            DriverManager.registerDriver(myDriver2);
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertStudentObject(Student student) {

        Connection conn = this.connect();
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet = null;
        String name = student.getName();
        String email = student.getEmail();
        String password = student.getPassword();

        try {
            String sql = "INSERT INTO Student(name, email, password) VALUES(?,?,?)";
            preparedStatement1 = conn.prepareStatement(sql);
            preparedStatement1.setString(1,name);
            preparedStatement1.setString(2,email);
            preparedStatement1.setString(3,password);
            preparedStatement1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        public void insertStudent(String name, String email, String password) {
        connect();
        String sql = "INSERT INTO Student(name, email, password) VALUES(?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTeacher(String name, String email, String password) {
        connect();
        String sql = "INSERT INTO Teacher(name, email, password) VALUES(?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public void ConnectCourseWithUser(Integer user, Integer course) {
        String sql = "INSERT INTO userCourses(userId, courseId VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user);
            pstmt.setInt(2, course);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ConnectCourseWithTeacher(Integer user, Integer course) {
        String sql = "INSERT INTO userCourses(userId, courseId VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user);
            pstmt.setInt(2, course);
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
    }
}
