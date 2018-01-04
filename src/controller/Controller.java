
package controller;

import dbCom.DBConnection;
import dbCom.*;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;


public class Controller {
    private CollectionOfUsers users;
    private User user;
    private UserManager userManager;
    private DBSelect dbSelect;
    private DBInsert dbInsert;
    private DBConnection dbConnection;

    public Controller() {
        this.users = UserManager.getAllUsers();
    }

    public boolean tryLogin(String email, String password, boolean isTeacher) {
        if (email.equals("Admin") && password.equals("Password")) {
            System.out.println("Hi admin");
            return true;
        } else {
            try {
                DBFetch dbFetch = new DBFetch();
                if (dbFetch.tryLogin(email, password, isTeacher)) {
                    if (isTeacher) {
                        System.out.println("Login teacher successful //controller");
                        return true;
                    } else {
                        System.out.println("Login student successful //controller");
                        return true;
                    }
                } else {
                    System.out.println("Login failed //controller");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Login user failed hard//controller");
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean tryisTeacher(String studentOrTeacher) {
        try {
            if (studentOrTeacher.equals("Teacher")){
                return true;
            } else {
                return false;
            }
        }
        catch (NullPointerException n) {
            System.out.println("NullPointerException in controller tryisTeacher");
        }
        return false;
    }

    public boolean tryRegister(String name, String email, String password, boolean isTeacher){
        // First try login
        if (tryLogin(email,password, isTeacher)) {
            System.out.println("Account already exists!");
            return false;
        // Check if user or teacher
        } else {
            if (isTeacher) {
                return false;
            } else {
                tryRegisterStudent(name,email,password);
            }
            return true;
        }
    }

    public boolean tryRegisterStudent(String name, String email, String password) {
        if (email.equals("Admin") && password.equals("Password")) {
            System.out.println("Hi admin is already registered");
            return false;
        } else {
            //System.out.println("Controller confirms a: " + userOrTeacher + " with the name: " + name + "  is trying to register");
            //TODO Register user...
            DBInsert dbInsert = new DBInsert();
            dbInsert.insertStudent(name,email,password);
            return true;
        }
    }
    public boolean tryRegisterTeacher(String name, String email, String password) {
        if (email.equals("Admin") && password.equals("Password")) {
            System.out.println("Hi admin is already registered");
            return false;
        } else {
            DBInsert dbInsert = new DBInsert();
            dbInsert.insertTeacher(name,email,password);
            return true;
        }
    }
    public String getNameByEmail(String email) {
        try {
            System.out.println(users.getUsers().size());
            System.out.println("Works");
        } catch (NullPointerException i) {
            System.out.println("Null");
            return "NULL found";
        }
        return "getNameByEmail end";
    }

    public ArrayList getCoursesAll() {
        try {
            ArrayList AllCourses;
            AllCourses = dbSelect.selectAllCourses();
            return AllCourses;
        } catch (Exception e) {
            return null;
        }
    }
    public String getCoursesMy(String email) {
        try {
            System.out.println("Trying to fetch my courses thru controller");
            return "Null of my courses found. Searching with email: " + email;
        } catch (Exception e) {
            System.out.println("EXCEPTION in controller getCoursesAll");
            return "Error";
        }
    }
    public String setCoursesCreateNew(String course) {
        System.out.println("Trying to register new course thru controller");
        System.out.println(course);
        DBInsert dbInsert = new DBInsert();
        dbInsert.insertCourse(course);
        return null;
        //return "Trying to register course: " + course + "";
    }

    public static void main(String[] args) {
        new Controller();
    }
}
    /*
        for(int i=0; i < users.getUsers().size();i++){
            if(users.getUsers().get(i).getEmail().equals(email)){
                return users.getUsers().get(i).getName();
            }
        }
        return "Name not found by email";

        NullPointerException
*/