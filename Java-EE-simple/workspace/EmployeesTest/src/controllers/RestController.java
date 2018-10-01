package controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import services.EmployeeService;
import web.models.WebEmployee;

@Path("employee")
public class RestController {
	
	@Inject
	private EmployeeService employeeService;

	@GET
	@Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public WebEmployee getEmployeeById(@QueryParam("empNo") Long empNo) {

        return this.employeeService.findEmployeeById(empNo);
    }
	
	@GET
	@Path("testAsync")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WebEmployee> getEmployeesAsync() throws InterruptedException, ExecutionException {

		return this.employeeService.findEmployeesAsync();
    }
}
