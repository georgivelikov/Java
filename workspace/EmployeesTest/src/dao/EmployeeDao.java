package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import models.Employee;

@Stateless
public class EmployeeDao {

	@PersistenceContext(unitName="EmployeesTest")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Employee> findEmployees(int limit) {
		return this.em.createNamedQuery(Employee.FIND_ALL).setMaxResults(limit).getResultList();
	}
}
