package interceptors;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class TextLoggerInterceptor {

	@AroundInvoke
	public Object log(InvocationContext context) throws Exception {
		
		Integer param = (Integer) context.getParameters()[0];
		String path = "C:\\Users\\georgivelikov\\Desktop\\log.txt";
		try(BufferedWriter bf = new BufferedWriter(new FileWriter(path, true))) {
			bf.write(System.lineSeparator() + "Number of employees searched: " + param);
		}
		
		return context.proceed();
	}
}
