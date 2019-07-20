package fr.casir.primefaces.view;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import fr.casir.primefaces.model.Student;
import fr.casir.primefaces.service.StudentService;
import javafx.scene.control.TableColumn.CellEditEvent;

@ManagedBean(name="dtView")
@ViewScoped
public class StudentView implements Serializable {
     
    public static List<Student> students;

    private List<Student> filteredStudents;
       
    
    @ManagedProperty("#{studentService}")
    private static StudentService service;
 
    @PostConstruct
    public void init() {
        try {
			students = service.createStudents();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
	// edit
	public void onRowEdit(RowEditEvent event) {
		Student newStudentValues = (Student) event.getObject();        
        students = service.updateStudent(newStudentValues);        
        
        FacesMessage msg = new FacesMessage("Student Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    
    public void setService(StudentService service) {
        this.service = service;
    }

	public StudentService getService() {
		return service;
	}

	

	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


	public List<Student> getFilteredStudents() {
		return filteredStudents;
	}


	public void setFilteredStudents(List<Student> filteredStudents) {
		this.filteredStudents = filteredStudents;
	}


	public void remove(Student student) {
	    try {
	    	filteredStudents.remove(student);
			System.out.println("Row deleted");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    
}