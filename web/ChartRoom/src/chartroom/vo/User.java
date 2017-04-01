/**
 *
 */
package chartroom.vo;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * <p>
 * Title:User
 * </p>
 * <p>
 * Description:用户bean， 这由于没有用扩展对象， 所以直接让User实现HttpSessionBindingListener接口，
 * 目的是在user对象添加到session跟从session中移除的时候执行servletContext里面的userMap的元素添加与移除操作
 * </p>
 * 
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public class User implements HttpSessionBindingListener {
	private Integer id;
	private String username;
	private String password;
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// 因为user对象要当做userMap的key,所以重写hashCode方法
	@Override
	public int hashCode() {
		return this.id;
	}

	// 重写equals，如果hashCode是一样的就执行equals方法
	@Override
	public boolean equals(Object obj) {
		// 如果是同一个对象
		if (obj == this)
			return true;

		// obj不存在 或者 obj不是User类的实例
		if (obj == null || !(obj instanceof User))
			return false;

		// 两个对象的id相同
		User anotherUser = (User) obj;
		if (this.getId() == anotherUser.getId())
			return true;
		return false;
	}

	/*
	 * 将JavaBean与Session绑定（也就是将一个对象存到Session）的时候自动执行。
	 * 这里最要的逻辑是将当前添加到session的用户跟相应的session存入userMap
	 * 
	 * @param event
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// 获取当前的session（也就是获取事件源，因为event是监听session产生的对象）
		HttpSession session = event.getSession();

		// 通过session获取servletContext
		ServletContext servletContext = session.getServletContext();

		// 获取servletContext里面的userMap
		@SuppressWarnings("unchecked")
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) servletContext
				.getAttribute("userMap");

		// 把当前用户跟相应的session存入userMap
		userMap.put(this, session);

		// 向聊天信息中添加一条公告，显示用户上线
		Object attribute = servletContext.getAttribute("messages");
		String messages = attribute == null ? "" : (String) attribute;

		// 新产生一条公告
		String newInfo = "<font color='red'>系统通知：</font><font color='teal'>" + this.username
				+ "</font><font color='blue'>上线啦.</font><br>";

		// 重写servletContext中的messages
		messages += newInfo;
		servletContext.setAttribute("messages", messages);
	}

	/*
	 * 将java对象与session解除绑定的时候自动执行。这里的功能是将当前的user 跟其session 键值对从userMap中移除
	 * 
	 * @param event
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// 获取当前的session
		HttpSession session = event.getSession();

		// 通过session获取servletContext
		ServletContext servletContext = session.getServletContext();

		// 获取servletContext里面的userMap
		@SuppressWarnings("unchecked")
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) servletContext
				.getAttribute("userMap");

		// 把当前用户从userMap中移除
		userMap.remove(this);

		// 向聊天信息中添加一条公告，显示用户下线
		Object attribute = servletContext.getAttribute("messages");
		String messages = attribute == null ? "" : (String) attribute;

		// 新产生一条公告
		String newInfo = "<font color='red'>系统通知：</font><font color='teal'>" + this.username
				+ "</font><font color='blue'>已经下线.</font><br>";

		// 重写servletContext中的messages
		messages += newInfo;
		servletContext.setAttribute("messages", messages);
	}

}
