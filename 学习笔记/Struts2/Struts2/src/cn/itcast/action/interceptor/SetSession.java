package cn.itcast.action.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class SetSession {
	private String loginName;
	private String loginResult = "登录失败";
	
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String setLogin(){
		Map<String,Object> sessionmap = ActionContext.getContext().getSession();
		if(loginName == null) loginName = "default";
		sessionmap.put("loginName", loginName);
		loginResult = "登录成功"+",用户名是："+loginName;
		return "login";
	}
	public String setLogout(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String loginNameInSession = (String) session.getAttribute("loginName");
		session.removeAttribute("loginName");
		loginResult = loginNameInSession+"已经logout";
		return "login";
	}
}
