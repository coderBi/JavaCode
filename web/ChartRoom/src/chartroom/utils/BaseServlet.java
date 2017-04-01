/**
 *
 */
package chartroom.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:BaseServlet</p>
 * <p>Description:自己定义的反射的servlet。继承自HttpServlet，页面传入method方法，通过反射判断当前的请求方法并执行</p>
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public class BaseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response){
		//设置传入的post编码，如果是get不能通过这个函数实现设置
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//设置相应的contentType
		response.setContentType("text/html;charset=utf-8");
		
		//获取前台传入的method方法
		String methodName = request.getParameter("method");
		
		//如果前台没有传入就默认执行excute方法
		if(methodName ==null || methodName.isEmpty()){
			methodName = "excute";
		}
		
		//得到当前执行的class。这个class是BaseServlet的子类
		Class<? extends BaseServlet> c = this.getClass();
		
		try{
			//通过反射获取方法并执行
			Method m = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			String result = (String) m.invoke(this, request, response);
			if(result != null && !result.isEmpty()){
				request.getRequestDispatcher(result).forward(request, response);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
