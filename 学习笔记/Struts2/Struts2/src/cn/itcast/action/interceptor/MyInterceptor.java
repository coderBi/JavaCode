package cn.itcast.action.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterceptor implements Interceptor {
	private static final long serialVersionUID = -9120906498153149777L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Object loginName = ActionContext.getContext().getSession()
				.get("loginName");
		if (null != loginName)
			actionInvocation.invoke();  //如果已经登录，就执行action方法
		ActionContext.getContext().put("message", "无权限进行操作");  //校验失败
		return "showError";
	}

}
