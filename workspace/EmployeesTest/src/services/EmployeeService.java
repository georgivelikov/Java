package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.EmployeeDao;
import models.Employee;

@Stateless
public class EmployeeService {
	
	@Inject
	private EmployeeDao employeeDao;
	
	public List<Employee> findEmployees(int limit) {
		return this.employeeDao.findEmployees(limit);
	}
}
