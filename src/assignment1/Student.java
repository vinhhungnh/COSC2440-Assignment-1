package assignment1;
import java.util.*;

public class Student {
    public String id;
    public String name;
    public String birthday;

    public Student(String id, String name, String birthday){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public String getId(){
        System.out.println(this.id);
        return this.id;
    }
}
