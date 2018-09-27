package starter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "/startController")
public class StartController {
	@Path(value = "/subpattern")
	@GET
	public String roaster(){
        return "subpattern.jsp";
    }
	
/*	@Path(value = "/subpattern2")
	@GET
	public String roaster(){
        //do whatever
        return new Viewable("home", some_model_object);
    }*/
}
