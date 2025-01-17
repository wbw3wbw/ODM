package logic;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserInterceptor extends AbstractInterceptor  {  
	//拦截Action处理的拦截方法  
	public String intercept(ActionInvocation invocation) throws Exception  {  
		Map map = invocation.getInvocationContext().getSession();
		if(map.get("LOGINBASICINFO") == null) {
			return "login";
		}else {
			return invocation.invoke();
		}
	}
}