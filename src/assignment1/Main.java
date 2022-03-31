package assignment1;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
	// write your code here
        Map<String, Student> studentMap = new HashMap<>();
        Map<String, Course> courseMap = new HashMap<>();
        StudentEnrolmentList list = new StudentEnrolmentList();
        try {
            File f = new File("default.csv");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                Student s = new Student(data[0], data[1], data[2]);
                studentMap.put(s.getId(), s);
                Course c = new Course(data[3], data[4], Integer.parseInt(data[5]));
                courseMap.put(c.getId(), c);
                StudentEnrolment se = new StudentEnrolment(s, c, data[6]);
                list.add(se);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Student s1 = studentMap.get("S101312");
        Course c1 = courseMap.get("COSC4030");
        list.add(new StudentEnrolment(s1, c1, "2020A"));
    }

}




