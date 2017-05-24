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
        ArrayList<String> publicWorks= new ArrayList<>();
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
            else if(group.equalsIgnoreCase("")&&student.getFirstName().equalsIgnoreCase(name)){
                findStudentArrayList.add(student);
            }
            else if(student.getGroup().equalsIgnoreCase(group)&&name.equalsIgnoreCase("")){
                findStudentArrayList.add(student);
            }
        }
        return  findStudentArrayList;
    }

    public ArrayList<Student> findStudentByNameAndWork(String name, String studWork){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for(Student student: studentBase.getStudents()){
            if(student.getFirstName().equalsIgnoreCase(name)||name.equalsIgnoreCase(""))
            {
                for(String work:student.getPublicWork()){
                    if(work.equalsIgnoreCase(studWork)){
                        findStudentArrayList.add(student);
                        break;
                    }
                }
            } else if (studWork.equalsIgnoreCase("")&&student.getFirstName().equalsIgnoreCase(name)){
                findStudentArrayList.add(student);
            }
        }
        return  findStudentArrayList;
    }

    public ArrayList<Student> findStudentByNameAndNumberOfWork(String name, String lowerLimit, String upperLimit){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for(Student student: studentBase.getStudents()){
            if(student.getFirstName().equalsIgnoreCase(name)||name.equalsIgnoreCase(""))
            {   ArrayList<String> workMassiv=new ArrayList<>();
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
            }else if (lowerLimit.equalsIgnoreCase("")&&upperLimit.equalsIgnoreCase("")&&student.getFirstName().equalsIgnoreCase(name)){
                findStudentArrayList.add(student);
            }
        }
        return  findStudentArrayList;
    }
    public int deleteStudentByNameAndGrop(String name, String group){
        int coll=studentBase.removeStudents(findStudentByNameAndGrop(name,group));

        return coll;
    }
    public int deleteStudentByNameAndWork(String name, String group){
        int coll=studentBase.removeStudents(findStudentByNameAndWork(name,group));
        return coll;
    }
    public int deleteStudentByNameAndNumberOfWork(String name, String lowerLimit,String upperLimit){
        int coll=studentBase.removeStudents(findStudentByNameAndNumberOfWork(name,lowerLimit,upperLimit));
        return coll;
    }

}
