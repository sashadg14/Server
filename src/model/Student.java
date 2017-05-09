package model;

import java.util.ArrayList;

/**
 * Created by alex o n 11.04.2017.
 */
public class Student {
    String firstName="";
    String lastName="";
    String middleName=" ";
    String group="";
    ArrayList<String> publicWork;

    public Student(){
        publicWork=new ArrayList<String>();
        for(int i=0;i<10;i++)
            publicWork.add("");
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPublicWork(ArrayList<String> publicWork) {
        this.publicWork = publicWork;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public ArrayList<String> getPublicWork() {
        return publicWork;
    }

}
