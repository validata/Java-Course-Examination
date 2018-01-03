package tests;
import model.Course;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCourse {
    int numberOfTests;

    @Test
    public void testCourses(){
        this.numberOfTests = 0;
        Course course = new Course(1,"CourseName");
        int courseID = course.getId();
        String courseName = course.getCourseName();

        assertEquals(1,courseID);
        this.numberOfTests++;
        assertEquals("CourseName",courseName);
        this.numberOfTests++;
        System.out.println("Number of tests completed: " + numberOfTests);
    }
}
