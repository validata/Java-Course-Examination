package dbCom;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:TheDB.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createAllTables() {
        // SQLite connection string
        String url = "jdbc:sqlite:TheDB.db";

        // SQL statement for creating a new table for users
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	email text NOT NULL,\n"
                + "	studentOrTeacher integer NOT NULL,\n"
                + ");";

        // SQL statement for creating a new table for courses
        String createCoursesTable = "CREATE TABLE IF NOT EXISTS courses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + ");";

        // SQL statement for creating a new table for courses
        String createUserCoursesTable = "CREATE TABLE IF NOT EXISTS userCourses (\n"
                + "	userId integer NOT NULL,\n"
                + "	courseId text NOT NULL,\n"
                + ");";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // create tables
            stmt.execute(createUsersTable );
            stmt.execute(createCoursesTable );
            stmt.execute(createUserCoursesTable );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewDatabase("TheDB.db");

        createAllTables();
    }
}
