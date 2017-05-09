package controllers;


import model.Student;
import model.StudentBase;

import java.util.ArrayList;

/**
 * Created by alex o n 20.04.2017.
 */
public class DataBaseManipulation {
    StudentBase studentBase;

    public DataBaseManipulation(StudentBase studentBase){
        this.studentBase=studentBase;
    }
    public void addNewStudentInBase(ArrayList<String> tempArray){
        Student newStudent= new Student();
        newStudent.setFirstName(tempArray.get(0));
        newStudent.setMiddleName(tempArray.get(1));
        newStudent.setLastName(tempArray.get(2));
        newStudent.setGroup(tempArray.get(3));
        ArrayList<String> publicWorks= new ArrayList<String>();
        for(int i=0; i<10;i++){
            publicWorks.add(tempArray.get(4+i));
        }
        newStudent.setPublicWork(publicWorks);
        studentBase.addStudent(newStudent);
        //System.out.println(studentBase.getStudents().get(0).getGroup()+" ------");
    }

    public ArrayList<Student> findStudentByNameAndGrop(String name, String group){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for(Student student: studentBase.getStudents()){
            if(student.getGroup().equalsIgnoreCase(group)&&student.getFirstName().equalsIgnoreCase(name))
            {
                findStudentArrayList.add(student);
            }
        }
        return  findStudentArrayList;
    }

    public ArrayList<Student> findStudentByNameAndWork(String name, String studWork){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for(Student student: studentBase.getStudents()){
            if(student.getFirstName().equalsIgnoreCase(name))
            {
                for(String work:student.getPublicWork()){
                    if(work.equalsIgnoreCase(studWork)){
                    findStudentArrayList.add(student);
                    break;
                    }
                }
            }
        }
        return  findStudentArrayList;
    }

    public ArrayList<Student> findStudentByNameAndNumberOfWork(String name, String lowerLimit, String upperLimit){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for(Student student: studentBase.getStudents()){
            if(student.getFirstName().equalsIgnoreCase(name))
            {int coll=0;
                    ArrayList<String> workMassiv=new ArrayList<>();
                for(String string:student.getPublicWork())
                {   boolean bool=false;
                    for(String string2:workMassiv){
                        if(string.equalsIgnoreCase(string2))
                            bool=true;
                    }
                    if(!bool)
                    workMassiv.add(new String(string));
                }
                ///System.out.println(workMassiv.size());
                if(workMassiv.size()-1>=Integer.parseInt(lowerLimit)&&workMassiv.size()-1<=Integer.parseInt(upperLimit))
                    findStudentArrayList.add(student);
            }
        }
        return  findStudentArrayList;
    }
    public void deleteStudentByNameAndGrop(String name, String group){
        studentBase.removeStudents(findStudentByNameAndGrop(name,group));
    }
    public void deleteStudentByNameAndWork(String name, String group){
        studentBase.removeStudents(findStudentByNameAndWork(name,group));
    }
    public void deleteStudentByNameAndNumberOfWork(String name, String lowerLimit,String upperLimit){
        studentBase.removeStudents(findStudentByNameAndNumberOfWork(name,lowerLimit,upperLimit));
    }

}
