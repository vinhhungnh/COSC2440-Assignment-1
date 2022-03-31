package assignment1;

import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {
    public ArrayList<StudentEnrolment> finalList = new ArrayList<>();

    public void display(){
        for(StudentEnrolment s: finalList){
            System.out.println(s.getStudentID()+s.getCourseID());
        }
    }

    @Override
    public void add(StudentEnrolment se) {
        for (StudentEnrolment a: finalList){
            if ((a.getStudentID()).equals(se.getStudentID()) && (a.getCourseID()).equals(se.getCourseID())){
                System.out.println("Student already enrolled this course");
            }else{
                finalList.add(se);
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }
}

interface StudentEnrolmentManager{
    public void add(StudentEnrolment se);
    public void update();
    public void delete();
    public void getOne();
    public void getAll();

}