package model;

import java.util.ArrayList;

public class Student extends User {

    private Boolean isTeacher;

    public Student(String name, String email, String password) {
        super(name, email, password);
        this.isTeacher = false;
    }

    public Student(String name, String email, String password, Boolean isTeacher) {
        super(name, email, password);
        this.isTeacher = isTeacher;
    }

    public Student(String name, String email, String password, String isTeacherOrUser, ArrayList<Course> coursesArrayList) {
        super(name, email, password, isTeacherOrUser, coursesArrayList);
        this.isTeacher = false;
    }
}
