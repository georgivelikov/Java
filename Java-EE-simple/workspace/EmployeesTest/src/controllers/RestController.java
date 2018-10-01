package controllers;

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
    @Produces(MediaType.APPLICATION_JSON)
    public WebEmployee getEmployeeById(@QueryParam("empNo") Long empNo) {

        return this.employeeService.findEmployeeById(empNo);
    }
}
