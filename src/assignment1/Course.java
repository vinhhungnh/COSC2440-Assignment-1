package assignment1;

public class Course {
    public String id;
    public String name;
    public int credit;

    public Course(String id, String name, int credit){
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public String getId(){
        return this.id;
    }
}
