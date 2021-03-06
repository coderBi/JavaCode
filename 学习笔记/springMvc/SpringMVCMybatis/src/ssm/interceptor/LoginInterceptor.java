/**
 *
 */
package ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title:HandlerInterceptor</p>
 * <p>Description:自定义拦截器</p>
 * @author Edwin
 * @since 2017-3-2
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

	//在Handler方法执行之后执行。可以使用统一的异常处理。 可以用于统一的日志处理（通过Exception ex是否有值，进行日志打印）
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor1....afterCompletion");
	}
	
	//进入handler方法之后在返回modelAndView之前执行。 可以将公用的模型数据传到视图（例如网站的导航菜单），也可以指定统一的视图。
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor1....postHandle");
	}

	//进入handler之前执行。 可以用于身份认证 身份授权。 return false 表示拦截  return true表示放行。
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//获取请求的url
		String requestURI = request.getRequestURI();
		
		//判断是否是公开的地址，如果是就是直接放行，不进行登录验证
		if(requestURI.indexOf("login.action") > 0){
			//如果是登录提交，直接放行
			return true;
		}
		
		//判断session
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username != null){
			//身份存在，已经登录
			return true;
		}
		
		//跳转到登录页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

}
