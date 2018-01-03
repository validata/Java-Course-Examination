package dbCom;

import java.sql.*;
import java.util.ArrayList;

public class DBSelect {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:@test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ArrayList<String> selectAllCourses(){
        String sql = "SELECT id, name FROM courses";
        ArrayList courseNames = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                courseNames.add(rs.getInt("id"),
                        rs.getString("name"));
                //System.out.println(rs.getInt("id") +  "\t" +
                //        rs.getString("name") + "\t");
            }
            return courseNames;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            courseNames = null;
            return courseNames;
        }
    }


}
