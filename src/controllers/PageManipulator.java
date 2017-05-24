package controllers;

import model.Student;

import java.util.ArrayList;

/**
 * Created by alex o n 23.04.2017.
 */
public class PageManipulator {
    private ArrayList<Student> studentArrayList;
    private int countOfStudentOnLists=5;
    private int noOfPage =0;
    private int noOfPageReserv =0;
    //private Table table;
    public PageManipulator(ArrayList<Student> studentArrayList){
        this.studentArrayList=studentArrayList;
        //this.table=table;
    }

    public void setNoOfPage(int noOfPage) {
        this.noOfPage = noOfPage;
    }
    public int getNoOfPage() {
        int countOfPages= studentArrayList.size() /countOfStudentOnLists;
        if (countOfStudentOnLists*(countOfPages)==0)
            return 0;
        if (countOfStudentOnLists*(countOfPages)==studentArrayList.size())
            return noOfPage+1;
        else return noOfPage+1;
    }
    public int getCountOfPages(){
        int countOfPages= studentArrayList.size() /countOfStudentOnLists;
        if (countOfStudentOnLists*(countOfPages)<studentArrayList.size())
            return countOfPages+1;
        else
        if(countOfPages!=0)
            return countOfPages;
        else return 0;
    }
    public int getCountOfStudentOnLists(){
        return countOfStudentOnLists;
    }
    public int getCountOfAllStuden(){
        return studentArrayList.size();
    }
    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
        noOfPage=noOfPageReserv;
    }
    public void setStudentListForFinding(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
        noOfPageReserv=noOfPage;
        noOfPage=0;
    }
    public void setCountOfStudentOnLists(int countOfStudentOnLists) {
        this.countOfStudentOnLists = countOfStudentOnLists;
    }
    public ArrayList<Student> returnPageOfStudents(){
        ArrayList<Student> pageOfStudents= new ArrayList<Student>();
        //int countOfPages=(int)studentArrayList.size()/countOfStudentOnLists;
        //System.out.println(countOfPages+" count");
            for(int i = noOfPage *countOfStudentOnLists; i<(noOfPage +1)*countOfStudentOnLists&&i<studentArrayList.size(); i++)
                pageOfStudents.add(studentArrayList.get(i));
        return pageOfStudents;
    }
    public void NextPage() {
        if (countOfStudentOnLists*(noOfPage +1)<studentArrayList.size())
        {   noOfPage++;
            //table.renderTable(returnPageOfStudents());
        }
    }
    public void PreviousPage(){
        if(noOfPage !=0){
            noOfPage--;
           // table.renderTable(returnPageOfStudents());
        }
    }
    public void FirstPage(){
        noOfPage =0;
        //table.renderTable(returnPageOfStudents());
    }
    public void LastPage(){
        int countOfPages=(int)studentArrayList.size()/countOfStudentOnLists;
        if (countOfStudentOnLists*(countOfPages)<studentArrayList.size())
        {   noOfPage =countOfPages;
       // System.out.println(countOfStudentOnLists*(countOfPages));
           // table.renderTable(returnPageOfStudents());
        }
    }
}
