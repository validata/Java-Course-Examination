package dbCom;

import model.Course;

import java.sql.*;
import java.util.ArrayList;

public class DBSelect {
    private static Connection connect() {
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

    public static ArrayList<Course> selectAllCourses(){
        System.out.println("Trying to select all courses");
        String sql = "SELECT name FROM Course";
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            // loop through the result set
            try {
                while (rs.next()) {
                    courses.add(new Course(rs.getString(1)));
                    System.out.println("1.. " + rs.getString(1));
                    //System.out.println(rs.getInt("id") +  "\t" +
                    //        rs.getString("name") + "\t");
                }
            } catch (NullPointerException n) {
                System.out.println("Error in DBSelect selecetAllCourses Null");
                System.out.println(n.getMessage());
                return null;
            }
            System.out.println(courses);
            return courses;
        } catch (SQLException e) {
            System.out.println("Error in DBSelect selectAllCourses SQL");
            System.out.println(e.getMessage());
            courses = null;
            return courses;
        } catch (NullPointerException n) {
            System.out.println(n.getMessage());
            return null;
        }
    }
}
