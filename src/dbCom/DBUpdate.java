package dbCom;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public boolean updateCourseTeacher(int teacherID, int courseID){
        // TODO : Try and excpt
        String sql = "UPDATE Course SET isTeacher WHERE courseID = courseID";
        return true;
    }
}
