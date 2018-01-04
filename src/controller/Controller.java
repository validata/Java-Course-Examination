package controller;

import dbCom.DBConnection;
import dbCom.*;
import model.*;
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
                try {
                    if (dbFetch.tryLogin(email, password, isTeacher)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                } catch (Exception e){
                        System.out.println("Error??");
                    }
                    return true;
            } catch (Exception e) {
                System.out.println("Login user failed hard//controller");
                return false;
            }
        }
    }

    public boolean tryRegister(String name, String email, String password, boolean isTeacher){
        // First try login
        if (tryLogin(email,password, isTeacher)) {
            System.out.println("Account already exists!");
            return false;
        // Check if user or teacher
        } else {
            if (isTeacher) {
               tryRegisterTeacher(name,email,password);
               return true;
            } else {
                tryRegisterStudent(name,email,password);
                return true;
            }
        }
    }

    public boolean tryRegisterStudent(String name, String email, String password) {
        if (email.equals("Admin") && password.equals("Password")) {
            System.out.println("Hi admin is already registered");
            return false;
        } else {
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
    // TODO not in use for now
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

    public ArrayList<Course> getCoursesAll() {
        try {
            ArrayList<Course> courses;
            courses = dbSelect.selectAllCourses();
            System.out.println("From controller: ");
            System.out.println(courses);
            return courses;
        } catch (Exception e) {
            System.out.println("Before null");
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
        //TODO : return "Trying to register course: " + course + "";
    }

    public boolean setCourseTeacher(String name, String email) {
        try {
            DBUpdate dbUpdate = new DBUpdate();
            dbUpdate.updateCourseTeacher(name, email);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void main(String[] args) {
        new Controller();
    }
}