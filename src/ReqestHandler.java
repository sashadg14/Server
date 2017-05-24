import controllers.DataBaseManipulation;
import controllers.PageManipulator;
import model.Student;
import model.StudentBase;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by alex o n 23.04.2017.
 */
public class ReqestHandler {
    BufferedReader in;
    PrintWriter out;
    StudentBase studentBase;
    DataBaseManipulation dataBaseManipulation;
    PageManipulator pageManipulator;
    PageManipulator pageManipulatorForFinding;
    private static final Logger log = Logger.getLogger(ReqestHandler.class);
    ReqestHandler(BufferedReader in, PrintWriter out) throws IOException {
        this.in=in;
        this.out=out;
        studentBase= new StudentBase();
        dataBaseManipulation= new DataBaseManipulation(studentBase);
        pageManipulator = new PageManipulator(studentBase.getStudents());
        pageManipulatorForFinding = new PageManipulator(null);
    }
    public void addRequest() throws IOException {
        String fclient;
        int count=0;
        ArrayList<String> strings=new ArrayList<String>();
        while (count<14) {
            if ((fclient = in.readLine()) != "") {
                strings.add(fclient);
                count++;
            }
        }
        dataBaseManipulation.addNewStudentInBase(strings);

    }

    public void getPageRequest(){
        returnPageToClient(pageManipulator);
    }
    public void setNumberOFStudentsOnPageRequest(){
        try {
            pageManipulator.setCountOfStudentOnLists(Integer.parseInt(in.readLine()));
        } catch (IOException e) {
            log.error(e.getMessage());
            //e.printStackTrace();
        }
    }
    private void returnPageToClient(PageManipulator pageManipulator){
        out.println(pageManipulator.returnPageOfStudents().size());
        out.println("Страница "+pageManipulator.getNoOfPage()+" из " +
                pageManipulator.getCountOfPages()+" на странице макс. "+pageManipulator.getCountOfStudentOnLists()+" из "+pageManipulator.getCountOfAllStuden());

        for(Student student:pageManipulator.returnPageOfStudents()) {
            out.println(student.getFirstName());
            out.println(student.getMiddleName());
            out.println(student.getLastName());
            out.println(student.getGroup());
            for(String string: student.getPublicWork()){
                out.println(string);
            }
        }
    }

    public void deleteByNameAndGroupRequest() throws IOException {
        pageManipulator.setNoOfPage(0);
        String fclient,name = "",group="";
            if ((fclient = in.readLine()) != ""){
                name=fclient;
            }
            if ((fclient = in.readLine()) != ""){
            group=fclient;
            }
        out.println(dataBaseManipulation.findStudentByNameAndGrop(name,group).size());
        dataBaseManipulation.deleteStudentByNameAndGrop(name,group);
    }
    public void deleteByNameAndWorkRequest() throws IOException {
        pageManipulator.setNoOfPage(0);
        String fclient,name = "",work="";
            if ((fclient = in.readLine()) != ""){
                name=fclient;
            }
            if ((fclient = in.readLine()) != ""){
                work=fclient;
            }
        out.println(dataBaseManipulation.findStudentByNameAndWork(name,work).size());
        dataBaseManipulation.deleteStudentByNameAndWork(name,work);
    }
    public void deleteByNameAndNumberOfWorkRequest() throws IOException {
        pageManipulator.setNoOfPage(0);
        String fclient,name = "",lowerLimit="",upperLimit="";
            if ((fclient = in.readLine()) != ""){
                name=fclient;
            }
            if ((fclient = in.readLine()) != ""){
            lowerLimit=fclient;
            }
            if ((fclient = in.readLine()) != ""){
            upperLimit=fclient;
            }
        out.println(dataBaseManipulation.findStudentByNameAndNumberOfWork(name,lowerLimit,upperLimit).size());
        dataBaseManipulation.deleteStudentByNameAndNumberOfWork(name,lowerLimit,upperLimit);
    }
    ///
    public void findByNameAndGroupRequest() throws IOException {
        String fclient,name = "",group="";
        if ((fclient = in.readLine()) != ""){
            name=fclient;
        }
        if ((fclient = in.readLine()) != ""){
            group=fclient;
        }
        pageManipulator.setStudentListForFinding(dataBaseManipulation.findStudentByNameAndGrop(name,group));
        //returnPageToClient(pageManipulatorForFinding);
    }
    public void findByNameAndWorkRequest() throws IOException {
        String fclient,name = "",work="";
        if ((fclient = in.readLine()) != ""){
            name=fclient;
        }
        if ((fclient = in.readLine()) != ""){
            work=fclient;
        }
        pageManipulator.setStudentListForFinding(dataBaseManipulation.findStudentByNameAndWork(name,work));
        //returnPageToClient(pageManipulatorForFinding);
    }
    public void findByNameAndNumberOfWorkRequest() throws IOException {
        String fclient,name = "",lowerLimit="",upperLimit="";
        if ((fclient = in.readLine()) != ""){
            name=fclient;
        }
        if ((fclient = in.readLine()) != ""){
            lowerLimit=fclient;
        }
        if ((fclient = in.readLine()) != ""){
            upperLimit=fclient;
        }
        pageManipulator.setStudentListForFinding(dataBaseManipulation.findStudentByNameAndNumberOfWork(name,lowerLimit,upperLimit));
        //returnPageToClient(pageManipulatorForFinding);
    }

    public void saveRequest() throws IOException {
        String fileName;
        if((fileName=in.readLine())!=null)
        studentBase.saveStudentBase(fileName);
    }
    public void loadRequest() throws IOException {
        String fileName;
        if((fileName=in.readLine())!=null){
        studentBase.readStudentBase(fileName);
        pageManipulator=new PageManipulator(studentBase.getStudents());
        }
    }
    //private
    public void nextPageRequest(){
        pageManipulator.NextPage();
    }
    public void previousPageRequest(){
        pageManipulator.PreviousPage();
    }
    public void firstPageRequest(){
        pageManipulator.FirstPage();
    }
    public void endFindingRequest(){
        pageManipulator.setStudentArrayList(studentBase.getStudents());
    }
    public void lastPageRequest(){
        pageManipulator.LastPage();
    }
}
