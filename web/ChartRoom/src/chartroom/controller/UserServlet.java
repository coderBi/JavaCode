/**
 *
 */
package chartroom.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import chartroom.service.UserService;
import chartroom.utils.BaseServlet;
import chartroom.vo.User;

/**
 * <p>
 * Title:UserServlet
 * </p>
 * <p>
 * Description:用户信息处理
 * </p>
 * 
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public class UserServlet extends BaseServlet {

	// 登录
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 通过一个map接受前天的username跟password
		Map<String, String[]> map = request.getParameterMap();

		User userReturned = null;
		try {
			User user = new User();

			// 利用Apache的BeanUtils将map中的内容封装到user 代替更多的set方法
			BeanUtils.populate(user, map);

			// 调用service处理
			UserService service = new UserService();
			userReturned = service.login(user);
			if (userReturned == null) {
				request.setAttribute("msg", "用户名或者密码错误.");
				return "/index.jsp";
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 登录成功，先将之前的session数据销毁 接触与之相关的所有对象绑定
		request.getSession().invalidate();

		// 判断userMap里面是否已经存在当前用户，如果存在进行先将其踢出（销毁session）
		// 获取userMap
		@SuppressWarnings("unchecked")
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) this
				.getServletContext().getAttribute("userMap");

		// 获取原来存在userMap中跟当前登录用户相同的session，这个session可能来自另一个浏览器
		HttpSession httpSession = userMap.get(userReturned);

		// 让之前这个用户的session失效，保证一个用户只对应一个生效的session
		if (null != httpSession)
			httpSession.invalidate();

		// 登录成功 保存session。 由于User类实现了HttpSessionBindingListener
		// 所以这里添加到session有隐含执行代码
		request.getSession().setAttribute("user", userReturned);

		ServletContext application = getServletContext();

		String message = "";
		if (null != application.getAttribute("message")) {
			message = application.getAttribute("message").toString();
		}

		message += "系统公告:<font color='gray'>" + userReturned.getUsername()
				+ "走进聊天室。</font><br>";
		application.setAttribute("message", message);

		// 登录成功，进行重定向，因为重定向了不需要再进行转发（重定向与转化不能共存）,所以返回一个null
		response.sendRedirect(request.getContextPath() + "/main.jsp");
		return null;
	}

	// 踢人下线
	public String kick(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 接受传入的id参数
		Integer id = Integer.parseInt(request.getParameter("id"));

		// 获取相应id对应的user
		User user = new UserService().selectById(id);

		// 获取userMap
		@SuppressWarnings("unchecked")
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) this
				.getServletContext().getAttribute("userMap");

		// 从userMap中获取相应用户对应的session
		HttpSession session = userMap.get(user);

		// 使user对应的session失效
		if (null != session)
			session.invalidate();

		// 重定向到main.jsp
		response.sendRedirect(request.getContextPath() + "/main.jsp");

		return null;
	}

	// 检查用户是否被踢下线 是返回 “be kicked out” 否返回 "still in"
	public String check(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取session
		HttpSession session = request.getSession();

		// 判断session中user是否存在 作为登录标志
		User user = (User) session.getAttribute("user");

		// 打给浏览器的内容
		String toWrite = "be kicked out";

		// 如果session中有用户信息，也就是已经登录
		if (null != user)
			toWrite = "still in";

		response.getWriter().write(toWrite);
		return null;
	}

	// 获取聊天天内容
	public String getMessages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取servletContext
		ServletContext servletContext = this.getServletContext();

		// 从servletContext中获取messages属性
		String messages = (String) servletContext.getAttribute("messages");

		if (null != messages)
			response.getWriter().write(messages);
		return null;
	}

	// 发送消息
	public String sendMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 接受参数
		// 发送者 也就是当前用户name
		String userFromName = request.getParameter("from");

		// 接受者名字
		String userToName = request.getParameter("to");

		// 表情
		String face = request.getParameter("face");

		// 信息颜色
		String color = request.getParameter("color");

		// 信息内容
		String content = request.getParameter("content");

		// 拼接一条聊天记录
		String newMessage = "<a href='javascript:' onclick=\"set('"
				+ userFromName + "')\"><font color='blue'><strong>"
				+ userFromName + "</strong></font></a><font color='#cc0000'>"
				+ face + "对<a href='javascript:' onclick=\"set('" + userToName
				+ "')\"></font><font color='green'>{" + userToName
				+ "}</font></a>说: <font color='" + color + "'>" + content
				+ "</font><br>";

		// 获取servletContext
		ServletContext servletContext = this.getServletContext();

		// 从servletContext中获取原来的messages。
		Object messagesObj = servletContext.getAttribute("messages");
		String messages = messagesObj == null ? "" : (String) messagesObj;

		// 将新消息添加到messages里面
		messages += newMessage;

		// 重新设置servletContext中的messages
		servletContext.setAttribute("messages", messages);
		
		//向浏览器打印 success表示发送成功
		response.getWriter().write("success");

		return null;
	}

	// 退出聊天室
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//销毁session
		HttpSession session = request.getSession();
		session.invalidate();
		
		//重定向到登录页面
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
		return null;
	}

}
