package assignment1;
import javax.annotation.processing.SupportedSourceVersion;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
	// write your code here
        Map<String, Student> studentMap = new HashMap<>();
        Map<String, Course> courseMap = new HashMap<>();
        StudentEnrolmentList seList = new StudentEnrolmentList();
        String file = "default.csv";

        System.out.println("Welcome to our Enrolment System");
        System.out.println("Please select a file: ");
        System.out.println("1. Choose my own file\n2. Use default.csv");
        Scanner input = new Scanner(System.in);
        String fileChoice = input.next();

        while (!fileChoice.equals("1") && !fileChoice.equals("2")){
            System.out.println("Please select a valid option:");
            System.out.println("1. Choose my own filepath\n2. Use default.csv");
            fileChoice = input.next();
        }
        if(fileChoice.equals("1")){
            System.out.println("Please enter file name or path: ");
            file = input.next();
        }


        while(file != null){
            try {
                File f = new File(file);
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] data = line.split(",");
                    Student s = new Student(data[0], data[1], data[2]);
                    studentMap.put(s.getId(), s);
                    Course c = new Course(data[3], data[4], Integer.parseInt(data[5]));
                    courseMap.put(c.getId(), c);
                    StudentEnrolment se = new StudentEnrolment(s, c, data[6]);
                    seList.populate(se);
                }
                sc.close();
                System.out.println("File imported successfully");
                file = null;
            } catch (FileNotFoundException e) {
                System.out.println("File cannot be found!\nPlease try again:");
                file = input.next();
            }
        }


        String func = "";
        while (!func.equals("0")){
            String choice = "";
            System.out.println("Please select a function: ");
            System.out.println("1. Enroll a student for one Semester\n2. Update an enrolment of a student for one Semester\n3. View all courses of a student in one Semester\n4. View all students of a course in one Semester\n5. View all courses offered in one Semester\n0. Exit the program");
            func = input.next();
            while (!func.equals("0") && !func.equals("1") && !func.equals("2") && !func.equals("3") && !func.equals("4") && !func.equals("5")){
                System.out.println("Please enter a valid option: ");
                func = input.next();
            }

            if (func.equals("1")){
                System.out.println("Enter ID of student:");
                String sid = input.next();
                System.out.println("Enter semester:");
                String sem = input.next();
                System.out.println("Enter ID of course:");
                String cid = input.next();
                try{
                    Student s = studentMap.get(sid.toUpperCase());
                    Course c = courseMap.get(cid.toUpperCase());
                    StudentEnrolment enrol = new StudentEnrolment(s,c,sem.toUpperCase());
                    seList.add(enrol);
                    System.out.println("Student " + s.id + " is enrolled successfully in " + c.id + " for semester " + sem+".");
                }catch(EnrolmentExistedException e){
                    System.out.println("Student already enrolled with this course!");
                }
            }

            if (func.equals("2")){
                String action = "";
                String cid = "";
                System.out.println("Enter ID of student:");
                String sid = input.next();
                System.out.println("Enter semester:");
                String sem = input.next();
                System.out.println("Courses of student " + sid + " in semester " + sem + ":");
                Student s = studentMap.get(sid);
                ArrayList<String> cList = seList.getAll(s);
                for(String str : cList){
                    String[] token = str.split(" - ");
                    if(token[3].equals(sem)){
                        System.out.println(str);
                    }
                }
                System.out.println("Select an action:\n1. Add\n2. Delete");
                choice = input.next();
                if(choice.equals("1")){
                    System.out.println("Enter course ID:");
                    cid = input.next();
                    Course c = courseMap.get(cid);
                    seList.update(s,c,sem,"add");
                }if(choice.equals("2")){
                    System.out.println("Enter course ID:");
                    cid = input.next();
                    Course c = courseMap.get(cid);
                    seList.update(s,c,sem,"delete");
                }
            }

            if(func.equals("3")){
                System.out.println("Enter student ID:");
                String sid = input.next();
                System.out.println("Enter semester:");
                String sem = input.next();
                Student s = studentMap.get(sid);
                ArrayList<String> cList = seList.getAll(s);
                System.out.println("Enrolled courses of student "+ sid + " in semester " + sem + ":");
                for(String str : cList){
                    String[] token = str.split(" - ");
                    if(token[3].equals(sem)){
                        System.out.println(str);
                    }
                }
            }

            if (func.equals("4")){
                System.out.println("Enter course ID:");
                String cid = input.next();
                System.out.println("Enter semester:");
                String sem = input.next();
                Course c = courseMap.get(cid);
                ArrayList<String> sList = seList.getAll(c);
                System.out.println("Enrolled students of course " + cid + " in semester " + sem + ":");
                for(String str: sList){
                    String[] token = str.split(" - ");
                    if(token[3].equals(sem)){
                        System.out.println(str);
                    }
                }
            }
        }
        seList.display();



    }

}




