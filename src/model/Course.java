package model;

import java.io.Serializable;

public class Course implements Serializable {
    private final int id;
    private String courseName;
    private static int nextID = 1;

    public Course(int id, String courseName) {
        this.courseName = courseName;
        this.id = nextID++;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
