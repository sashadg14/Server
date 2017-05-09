package fileWorking;

import model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by alex o n 23.04.2017.
 */
public class MyHandler extends DefaultHandler{
    String tempData;
    Student tempStudent;
    ArrayList<Student> studentArrayList;
    MyHandler(ArrayList<Student> studentArrayList){
        tempStudent=new Student();
        this.studentArrayList=studentArrayList;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tempData="";
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("firstName"))
            tempStudent.setFirstName(tempData);
        if (qName.equalsIgnoreCase("middleName"))
            tempStudent.setMiddleName(tempData);
        if (qName.equalsIgnoreCase("lastName"))
            tempStudent.setLastName(tempData);
        if (qName.equalsIgnoreCase("group"))
            tempStudent.setGroup(tempData);
        for(int i=1;i<=10;i++){
            if (qName.equalsIgnoreCase("semester" + i))
                tempStudent.getPublicWork().set(i-1,tempData);
        }
        if (qName.equalsIgnoreCase("student")) {
            studentArrayList.add(tempStudent);
            tempStudent=new Student();
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        tempData=new String(ch,start,length);
        //System.out.println(tempData);
    }
}

