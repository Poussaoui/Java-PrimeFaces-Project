package fr.casir.primefaces.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.casir.primefaces.model.Student;
import fr.casir.primefaces.service.parser.StudentDomParser;


@ManagedBean(name = "studentService")
@ApplicationScoped
public class StudentService implements Serializable {
    

	public static ArrayList<Student> students = new ArrayList<Student>();
	
    public List<Student> createStudents() throws Exception {
    	// get students list from XML file 
    	StudentDomParser studentDomParser = new StudentDomParser("G:\\eclipse-workspace\\PrimeFacesProject\\students.xml");
        List<Student> students = studentDomParser.getAllStudentsFromXML();
        return students;
    }
    
    public static List<Student> addStudent(Student student) {
		StudentDomParser studentDomParser = new StudentDomParser("G:\\eclipse-workspace\\PrimeFacesProject\\students.xml");
		List<Student> students = studentDomParser.addStudent(student);
		return students;
    }
    
    public static List<Student> updateStudent(Student student) {
		StudentDomParser studentDomParser = new StudentDomParser("G:\\eclipse-workspace\\PrimeFacesProject\\students.xml");
		List<Student> students = studentDomParser.updateStudent(student);
		return students;
    }
    
     
}