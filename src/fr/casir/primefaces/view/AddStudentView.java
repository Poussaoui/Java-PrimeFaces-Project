package fr.casir.primefaces.view;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import fr.casir.primefaces.model.Student;
import fr.casir.primefaces.service.StudentService;
import fr.casir.primefaces.service.parser.StudentDomParser;

@ManagedBean
public class AddStudentView {

	/*
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	*/
	
    private Integer id;
    private String firstName;
    private String lastName;
    private String mail;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void add() {
		FacesMessage faceMessage = null;
		try {

			System.out.println("LAST_ID :" + Student.LAST_ID);
			
			Student student = new Student(Student.LAST_ID, firstName, lastName, mail);
			StudentView.students = StudentService.addStudent(student);
			
			faceMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Add", "Adding with success");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		} catch (Exception e) {
			faceMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Adding Error", "Adding Failure");
			FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		}
	}
}