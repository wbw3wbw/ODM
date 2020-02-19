package logic;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BgInterceptor extends AbstractInterceptor  {  
	//����Action��������ط���  
	public String intercept(ActionInvocation invocation) throws Exception  {  
		Map map = invocation.getInvocationContext().getSession();
		if(map.get("LOGINADMIN") == null) {
			return Action.LOGIN;
		}else {
			return invocation.invoke();
		}
	}

}