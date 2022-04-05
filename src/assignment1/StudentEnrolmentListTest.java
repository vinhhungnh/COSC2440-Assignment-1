package assignment1;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentEnrolmentListTest {
    Student s1,s2,s3;
    Course c1,c2,c3;
    StudentEnrolment se1,se2,se3,se4;
    StudentEnrolmentList seList;
    @org.junit.Before
    public void setUp() throws Exception {
        s1 = new Student("S12345", "Nguyen Van A", "1/1/2000");
        s2 = new Student("S67890", "Nguyen Van B", "2/2/2000");
        s3 = new Student("S24680", "Nguyen Thi C", "9/6/2001");
        c1 = new Course("COSC1234", "Intro to Prog", 6);
        c2 = new Course("COSC5678", "Programming 1", 6);
        c3 = new Course("COSC2440", "Further Programming", 6);
        se1 = new StudentEnrolment(s1,c2,"2020A");
        se2 = new StudentEnrolment(s2,c1,"2020B");
        se3 = new StudentEnrolment(s3,c3,"2020A");
        se4 = new StudentEnrolment(s2,c2,"2020C");
        seList = new StudentEnrolmentList();
        seList.populate(se1);
        seList.populate(se2);
        seList.populate(se3);
    }

    @org.junit.Test
    public void populate() {
        seList.populate(se4);
        int size = seList.finalList.size();
        int expectedSize = 4;
        assertEquals(expectedSize,size);
    }

    @org.junit.Test
    public void add() {
        try{
            seList.add(se4);
            seList.add(se3);
            seList.add(se1);
        }catch(EnrolmentExistedException e){e.getMessage();}
        int size = seList.finalList.size();
        int expectedSize = 4;
        assertEquals(expectedSize,size);
    }

    @org.junit.Test
    public void update() {
        seList.update(s1,c2,"2020A","delete");
        seList.update(s1,c1,"2020A","add");
        seList.update(s2,c2,"2020B","add");
        int size = seList.finalList.size();
        int expectedSize = 4;
        assertEquals(expectedSize,size);
    }

    @org.junit.Test
    public void delete() {
        seList.delete("S12345", "COSC5678", "2020A");
        int size = seList.finalList.size();
        int expectedSize = 2;
        assertEquals(expectedSize,size);

    }

    @org.junit.Test
    public void getOne() {
        boolean check1 = seList.getOne(s1,c2,"2020A");
        boolean check2 = seList.getOne(s3,c1,"2020C");
        assertTrue(check1);
        assertFalse(check2);

    }

    @org.junit.Test
    public void getAll() {
        seList.populate(se4);
        ArrayList<String> expect1 = new ArrayList<>();
        ArrayList<String> expect2 = new ArrayList<>();
        expect1.add("COSC1234 - Intro to Prog - 6 - 2020B");
        expect1.add("COSC5678 - Programming 1 - 6 - 2020C");
        expect2.add("S12345 - Nguyen Van A - 1/1/2000 - 2020A");
        expect2.add("S67890 - Nguyen Van B - 2/2/2000 - 2020C");
        ArrayList<String> actual1 = seList.getAll(s2);
        ArrayList<String> actual2 = seList.getAll(c2);
        assertEquals(expect1,actual1);
        assertEquals(expect2,actual2);

    }
}