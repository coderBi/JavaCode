/**
 *
 */
package ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title:CustomExceptionResolver</p>
 * <p>Description:自定义的全局异常处理器，springmvc只支持定义一个</p>
 * @author Edwin
 * @since 2017-3-1
 * @version 1.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{
	/**
	 * <p>Title:resolveException</p>
	 * <p>Description:</p>
	 * @param request
	 * @param response
	 * @param handler  这个handler就是处理器适配器执行的handler，里面只有一个方法
	 * @param ex 抛出的异常
	 * @return
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		CustomException customException = null;
		if(ex instanceof CustomException){
			//如果是系统自定义的异常
			customException = (CustomException)ex;
		} else {
			//不是系统自定义异常，说明抛出了运行时异常。这个时候显示 “未知异常”
			customException = new CustomException("未知异常");
		}
		
		//得到错误信息
		String message = customException.getMessage();
		
		//将异常信息添加到modelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		
		//设置视图
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
