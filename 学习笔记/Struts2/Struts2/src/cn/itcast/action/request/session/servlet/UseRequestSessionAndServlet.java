package cn.itcast.action.request.session.servlet;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class UseRequestSessionAndServlet {
	public String use() {
		ActionContext ctx = ActionContext.getContext();
		ctx.getApplication().put("app", "app String");
		ctx.getSession().put("session", "session String");
		ctx.put("request", "request String");
		return "use";
	}

	
	public String anotherUse() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext servletContext = ServletActionContext
				.getServletContext(); // request.getServletContext();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		request.setAttribute("request", "another use, request");
		servletContext.setAttribute("app", "another use, servlet");
		session.setAttribute("session", "another use, session");
		request.setAttribute("names",Arrays.asList("张三","李四","王五"));
		return "use";
	}
}
