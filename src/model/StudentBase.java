package model;

import fileWorking.Parser;

import java.util.ArrayList;

/**
 * Created by alex o n 20.04.2017.
 */
public class StudentBase {
    private ArrayList<Student> students;
    private ArrayList<Student> pageOfStudents;
    private int countOfLists;
    public StudentBase(){
        students = new ArrayList<Student>();
    }
    public void addStudent(Student student){
        students.add(student);
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void removeStudents(ArrayList<Student> studentArrayList){
        for(Student student:studentArrayList){
            students.remove(student);
        }
    }
    public void saveStudentBase(String fileName)
    {
        new Parser().writeFile(students,fileName);
    }
    public void readStudentBase(String fileName){
        if(new Parser().readFromFile(fileName).size()!=0)
        students=new Parser().readFromFile(fileName);
    }
}
