package services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.transaction.Transactional;

import dao.EmployeeDao;
import models.Employee;
import web.models.WebEmployee;

@Stateless
public class EmployeeService {
	
	@Inject
	private EmployeeDao employeeDao;
	
	@Resource(lookup="java:jboss/ee/concurrency/executor/default") 
	ManagedExecutorService executor;
	
	@Transactional
	//@Interceptors(TextLoggerInterceptor.class)
	public List<WebEmployee> findEmployees(int limit) {
		List<Employee> employees = this.employeeDao.findEmployees(limit);
		List<WebEmployee> webEmployees = new ArrayList<>();
		
		for (Employee employee : employees) {
			WebEmployee we = new WebEmployee(employee);
			webEmployees.add(we);
		}
		
		return webEmployees;
	}
	
	@Transactional
	public WebEmployee findEmployeeById(Long empNo) {
		Employee e = this.employeeDao.findEmployeeById(empNo);
		
		return new WebEmployee(e);
	}
	
	@Transactional
	public List<WebEmployee> findEmployeesAsync() throws InterruptedException, ExecutionException {
		
		List<Callable<List<WebEmployee>>> callableList = new ArrayList<>();
		callableList.add(this.findEmployeesAsyncPrivate(5));
		callableList.add(this.findEmployeesAsyncPrivate(5));
		callableList.add(this.findEmployeesAsyncPrivate(5));
		
		List<Future<List<WebEmployee>>> results = executor.invokeAll(callableList);
		List<WebEmployee> list1 = results.get(0).get();
        List<WebEmployee> list2 = results.get(1).get();
        List<WebEmployee> list3 = results.get(2).get();
        
        List<WebEmployee> list = new ArrayList<>();
        
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        
        return list;
	}
	
	@Transactional
	//@Interceptors(TextLoggerInterceptor.class)
	private Callable<List<WebEmployee>> findEmployeesAsyncPrivate(int limit) throws InterruptedException {
		
		
		
		return new Callable<List<WebEmployee>>() {

			@Override
			public List<WebEmployee> call() throws Exception {
				List<Employee> employees = employeeDao.findEmployeesAsync(limit);
				List<WebEmployee> webEmployees = new ArrayList<>();
				
				for (Employee employee : employees) {
					WebEmployee we = new WebEmployee(employee);
					webEmployees.add(we);
				}

				return webEmployees;
			}	
		};	
	}
}
