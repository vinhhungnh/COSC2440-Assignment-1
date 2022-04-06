package assignment1;
import com.opencsv.CSVWriter;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Create Maps for Students and Courses, declare new StudentEnrolmentList object
        Map<String, Student> studentMap = new HashMap<>();
        Map<String, Course> courseMap = new HashMap<>();
        StudentEnrolmentList seList = new StudentEnrolmentList();
        String file = "default.csv"; // default file name
        // File selection
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
        // Read from .csv file and populate data
        while(file != null){
            try {
                File f = new File(file);
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] data = line.split(",");
                    Student s = new Student(data[0], data[1], data[2]);
                    studentMap.put(s.id, s);
                    Course c = new Course(data[3], data[4], Integer.parseInt(data[5]));
                    courseMap.put(c.id, c);
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

        // Main functions of the program
        String func = "";
        while (!func.equals("0")){
            String choice;
            // Main menu
            System.out.println("\nPlease select a function: ");
            System.out.println("1. Enroll a student for one Semester\n2. Update an enrolment of a student for one Semester\n3. View all courses of a student in one Semester\n4. View all students of a course in one Semester\n5. View all courses offered in one Semester\n0. Exit the program & Export");
            func = input.next();
            while (!func.equals("0") && !func.equals("1") && !func.equals("2") && !func.equals("3") && !func.equals("4") && !func.equals("5")){
                System.out.println("Please enter a valid option: ");
                func = input.next();
            }
            // Function 1: Add new enrolment
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
                    System.out.println("\nStudent " + s.id + " is enrolled successfully in " + c.id + " for semester " + sem+".");
                }catch(EnrolmentExistedException e){
                    System.out.println("\nStudent already enrolled with this course!");
                }catch(NullPointerException ne){
                    System.out.println("\nStudent or course can not be found!");
                }
            }
            // Function 2: Update an enrolment
            if (func.equals("2")){
                String cid;
                System.out.println("\nEnter ID of student:");
                String sid = input.next().toUpperCase();
                System.out.println("Enter semester:");
                String sem = input.next().toUpperCase();

                Student s = studentMap.get(sid);
                ArrayList<String> cList = seList.getAll(s);
                ArrayList<String> filteredList = new ArrayList<>();
                for(String str : cList){
                    String[] token = str.split(" - ");
                    if(token[3].equals(sem)){
                        filteredList.add(str);
                    }}
                if(filteredList.isEmpty()){
                    System.out.println("\nNo records of student " + sid + " in semester " + sem);
                }else {
                    System.out.println("\nCourses of student " + sid + " in semester " + sem + ":\n");
                    for(String strg:filteredList) {
                        System.out.println(strg);
                    }
                    System.out.println("\nSelect an action:\n1. Add\n2. Delete");
                    choice = input.next();
                    if(choice.equals("1")){
                        System.out.println("Enter course ID:");
                        cid = input.next().toUpperCase();
                        Course c = courseMap.get(cid);
                        seList.update(s,c,sem,"add");
                    }if(choice.equals("2")){
                        System.out.println("Enter course ID:");
                        cid = input.next().toUpperCase();
                        Course c = courseMap.get(cid);
                        seList.update(s,c,sem,"delete");
                    }
                }

            }
            // Function 3: View all courses of a student in a semester
            if(func.equals("3")){
                System.out.println("\nEnter student ID:");
                String sid = input.next().toUpperCase();
                System.out.println("Enter semester:");
                String sem = input.next().toUpperCase();
                Student s = studentMap.get(sid);
                ArrayList<String> cList = seList.getAll(s);
                ArrayList<String> filteredList = new ArrayList<>();
                for(String str : cList){
                    String[] token = str.split(" - ");
                    if(token[3].equals(sem)) {
                        filteredList.add(str);}}
                if (filteredList.isEmpty()) {
                    System.out.println("\nNo records of student " + sid + " in semester " + sem);
                }else{
                    System.out.println("\nEnrolled courses of student " + sid + " in semester " + sem + ":\n");
                    for(String strg: filteredList){
                        System.out.println(strg);
                    }
                    System.out.println("\nDo you wish to save this record to .csv file?");
                    System.out.println("1. Yes\n2. No");
                    choice = input.next();
                    if (choice.equals("1")){
                        String[] header = {"id","name","credit","semester"};
                        String fileName = sid + "-" + sem + ".csv";
                        export(filteredList, fileName,header);
                    }
                }
            }
            // Function 4: View all students of a course in a semester
            if (func.equals("4")){
                System.out.println("\nEnter course ID:");
                String cid = input.next().toUpperCase();
                System.out.println("Enter semester:");
                String sem = input.next().toUpperCase();
                Course c = courseMap.get(cid);
                ArrayList<String> sList = seList.getAll(c);
                ArrayList<String> filteredList = new ArrayList<>();
                for(String str: sList){
                    String[] token = str.split(" - ");
                    if(token[3].equals(sem)){
                        filteredList.add(str);
                    }
                }
                if(filteredList.isEmpty()){
                    System.out.println("\nNo records of course " + cid + " in semester " + sem);
                }else{
                    System.out.println("\nEnrolled students of course " + cid + " in semester " + sem + ":\n");
                    for(String strg: filteredList){
                        System.out.println(strg);
                    }
                    System.out.println("\nDo you wish to save this record to .csv file?");
                    System.out.println("1. Yes\n2. No");
                    choice = input.next();
                    if (choice.equals("1")){
                        String[] header = {"id","name","birthday","semester"};
                        String fileName = cid + "-" + sem + ".csv";
                        export(filteredList, fileName,header);
                    }
                }
            }
            // Function 5: View all courses offered in a semester
            if(func.equals("5")){
                Set<String> set = new HashSet<>();
                System.out.println("\nEnter semester:");
                String sem = input.next().toUpperCase();
                for (StudentEnrolment se: seList.finalList){
                    if (se.semester.equals(sem)){
                        set.add(se.getCourseID() + " - " +se.getCourseName() + " - " + se.getCourseCredit()) ;
                    }
                }
                ArrayList<String> offeredCourse = new ArrayList<>(set);
                if(offeredCourse.isEmpty()){
                    System.out.println("\nNo courses is offered in semester " + sem);
                }else{
                    System.out.println("\nCourses offered in semester " + sem + ":");
                    for (String str: offeredCourse){
                        System.out.println(str);
                    }
                    System.out.println("\nDo you wish to save this record to .csv file?");
                    System.out.println("1. Yes\n2. No");
                    choice = input.next();
                    if (choice.equals("1")){
                        String[] header = {"id","name","credit"};
                        String fileName = sem + "-Courses.csv";
                        export(offeredCourse, fileName,header);
                    }
                }
            }
        }
        // Export all data to FinalStudentEnrolment.csv file
        String[] header = {"sid","sname","birthday","cid","cname","credit","sem"};
        ArrayList<String> exportList = new ArrayList<>();
        for(StudentEnrolment se: seList.finalList){
            exportList.add(se.getStudentID() + " - " + se.getStudentName() + " - " + se.getStudentBd() + " - " + se.getCourseID() + " - " + se.getCourseName() + " - " + se.getCourseCredit() + " - " + se.semester);
        }
        export(exportList, "FinalStudentEnrolment.csv",header);

    }

    public static void export(ArrayList<String> list, String filepath, String[] header){
        File f = new File(filepath);
        try{
            FileWriter output = new FileWriter(f);
            CSVWriter writer = new CSVWriter(output);
            writer.writeNext(header);
            for (String str: list){
                String[] data = str.split(" - ");
                writer.writeNext(data);
            }
            writer.close();
            System.out.println("File saved successfully to " + filepath);
        }catch(IOException e){
            System.out.println("There was an error! Please try again!");
        }
    }

}




