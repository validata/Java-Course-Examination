package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User {

    private final int id;
    private String name;
    private String email;
    private String password;
    private String userOrTeacher;
    private ArrayList<Course> courseArrayList;
    private static int nextID = 1;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.courseArrayList = new ArrayList<>();
        this.id = nextID++;
    }

    public User(String name, String email, String password, String userOrTeacher, ArrayList<Course> courseArrayList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userOrTeacher = userOrTeacher;
        this.courseArrayList = courseArrayList;
        this.id = nextID++;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserOrTeacher() {
        return userOrTeacher;
    }

    public void setUserOrTeacher(String userOrTeacher) {
        this.userOrTeacher = userOrTeacher;
    }

    public ArrayList<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public void setCourseArrayList(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return
                Objects.equals(getEmail(), user.getEmail()) &&
                        Objects.equals(getPassword(), user.getPassword()) &&
                        Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getPassword());

    }
}
