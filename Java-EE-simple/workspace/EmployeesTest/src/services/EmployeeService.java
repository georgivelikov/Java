package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import dao.EmployeeDao;
import models.Employee;
import web.models.WebEmployee;

@Stateless
public class EmployeeService {
	
	@Inject
	private EmployeeDao employeeDao;
	
	@Transactional
	public List<WebEmployee> findEmployees(int limit) {
		List<Employee> employees = this.employeeDao.findEmployees(limit);
		List<WebEmployee> webEmployees = new ArrayList<>();
		
		for (Employee employee : employees) {
			WebEmployee we = new WebEmployee(employee);
			webEmployees.add(we);
		}
		
		return webEmployees;
	}
}
