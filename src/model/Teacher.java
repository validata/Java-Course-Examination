package model;

import java.util.ArrayList;

public class Teacher extends User {

    private Boolean isTeacher;

    public Teacher(String name, String email, String password) {
        super(name, email, password);
        this.isTeacher = true;
    }

    public Teacher(String name, String email, String password, String isTeacherOrUser, ArrayList<Course> coursesArrayList) {
        super(name, email, password, isTeacherOrUser, coursesArrayList);
        this.isTeacher = true;
    }
}
