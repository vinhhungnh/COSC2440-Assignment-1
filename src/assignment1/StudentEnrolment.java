package assignment1;

import java.util.ArrayList;
import java.util.Set;

public class StudentEnrolment {
    public Student s;
    public Course c;
    public String semester;

    public StudentEnrolment(Student s, Course c, String semester){
        this.s = s;
        this.c = c;
        this.semester = semester;
    }
    public String getStudentID(){
        return s.getId();
    }
    public String getCourseID(){
        return c.getId();
    }

}

