package assignment1;

import java.util.ArrayList;

public class StudentEnrolmentList implements StudentEnrolmentManager {
    public ArrayList<StudentEnrolment> finalList = new ArrayList<>();


    public void populate(StudentEnrolment se){
        finalList.add(se);
    }

    @Override
    public void add(StudentEnrolment se) throws EnrolmentExistedException {
        for (StudentEnrolment a: finalList){
            if ((a.getStudentID()).equals(se.getStudentID()) && (a.getCourseID()).equals(se.getCourseID())){
                throw new EnrolmentExistedException("Student already enrolled with this course.");
            }
            }
        finalList.add(se);
    }

    @Override
    public void update(Student s, Course c, String sem, String action) {
        if (action.equals("add")){
            StudentEnrolment se = new StudentEnrolment(s,c,sem);
            finalList.add(se);
            System.out.println("\nCourse added successfully!");
        }else{
            finalList.removeIf(se -> se.getStudentID().equals(s.id) && se.getCourseID().equals(c.id) && se.semester.equals(sem));
            System.out.println("\nCourse removed successfully!");
        }
    }

    @Override
    public void delete(String sid, String cid, String sem) {
        finalList.removeIf(se -> se.getStudentID().equals(sid) && se.getCourseID().equals(cid) && se.semester.equals(sem));
    }

    @Override
    public boolean getOne(Student s, Course c, String sem) {
        boolean record = false;
        for(StudentEnrolment se : finalList){
            if(se.getStudentID().equals(s.id) && se.getCourseID().equals(c.id) && se.semester.equals(sem)){
               record = true;
            }
        }
        return record;
    }

    @Override
    public ArrayList<String> getAll(Object o) {
        ArrayList<String> res = new ArrayList<>();
        if(o instanceof Student){
            for (StudentEnrolment se : finalList){
                if(se.getStudentID().equals(((Student) o).id)){
                    res.add(se.getCourseID() + " - " + se.getCourseName() + " - " + se.getCourseCredit() + " - " + se.semester);
                }
            }
        }if(o instanceof Course){
            for (StudentEnrolment se: finalList){
                if(se.getCourseID().equals(((Course)o).id)){
                    res.add(se.getStudentID()+ " - " + se.getStudentName() + " - " + se.getStudentBd() + " - " + se.semester);
                }
            }
        }
        return res;
    }
}

interface StudentEnrolmentManager{
    public void add(StudentEnrolment se) throws EnrolmentExistedException;
    public void update(Student s, Course c, String sem, String action);
    public void delete(String sid, String cid, String sem);
    public boolean getOne(Student s, Course c, String sem);
    public ArrayList<String> getAll(Object o);

}

class EnrolmentExistedException extends Exception{
    public EnrolmentExistedException(String errorMessage){
        super(errorMessage);
    }
}