/**
 *
 */
package chartroom.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import chartroom.vo.User;

/**
 * <p>
 * Title:MyListener
 * </p>
 * <p>
 * Description:自定义的监听器 用于监听ServletContext的创建和销毁的
 * </p>
 * 
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public class MyListener implements ServletContextListener {
	/*
	 * servletContext销毁的时候执行
	 * 
	 * @param sce 监听的ServletContext事件对象，是事件源对象servletContext发生改变的事件对象
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/*
	 * servletContext创建的时候自动执行
	 * 
	 * @param sce 事件对象
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Map<User, HttpSession> userMap = new HashMap<User, HttpSession>();

		// 通过事件对象获得事件源对象
		ServletContext servletContext = sce.getServletContext();

		// 向servletContext添加userMap属性，用于存放当前登录的用户集合
		servletContext.setAttribute("userMap", userMap);
	}

}
