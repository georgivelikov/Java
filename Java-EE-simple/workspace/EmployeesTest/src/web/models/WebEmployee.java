package web.models;

import java.util.ArrayList;
import java.util.List;

import models.Department;
import models.Employee;

public class WebEmployee {
	private String fullName;
	private List<Department> departments;
	
	public WebEmployee(Employee e) {
		this.fullName = e.getFirstName() + " " + e.getLastName();
		this.departments = new ArrayList<Department>(e.getDepartments());
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
}
