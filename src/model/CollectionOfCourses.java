package model;
import java.io.Serializable;
import java.util.ArrayList;

public class CollectionOfCourses implements Serializable{

    private ArrayList<Course> courses;

    public CollectionOfCourses(ArrayList<Course> course) {
        this.courses = course;
    }

    public CollectionOfCourses(){
        this.courses = new ArrayList<>();
    }

    public boolean addCourse(Course course){
        //TODO replace with for each:
        for(int i=0 ; i<courses.size() ; i++){
            if(course.getId() == courses.get(i).getId()){
                return false;
            }
        }
        courses.add(course);
        return true;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "CollectionOfCourses{" +
                "courses=" + courses +
                '}';
    }
}
