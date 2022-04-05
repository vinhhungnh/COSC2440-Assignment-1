package assignment1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentEnrolmentTest {
    Student s;
    Course c;
    StudentEnrolment se;
    @Before
    public void setUp() throws Exception {
        s = new Student("S123456", "Nguyen Van A", "1/1/2000");
        c = new Course("COSC2440", "Further Programming", 6);
        se = new StudentEnrolment(s,c,"2021A");
    }

    @Test
    public void getStudentID() {
        String id = "S123456";
        assertEquals(id, se.getStudentID());
    }

    @Test
    public void getStudentName() {
        String name = "Nguyen Van A";
        assertEquals(name, se.getStudentName());
    }

    @Test
    public void getStudentBd() {
        String bd = "1/1/2000";
        assertEquals(bd, se.getStudentBd());
    }

    @Test
    public void getCourseID() {
        String cid = "COSC2440";
        assertEquals(cid, se.getCourseID());
    }

    @Test
    public void getCourseName() {
        String cname = "Further Programming";
        assertEquals(cname, se.getCourseName());
    }

    @Test
    public void getCourseCredit() {
        int credit = 6;
        assertEquals(credit, se.getCourseCredit());
    }
}