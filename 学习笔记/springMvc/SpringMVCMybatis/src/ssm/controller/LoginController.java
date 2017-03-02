/**
 *
 */
package ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title:LoginController</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2017-3-2
 * @version 1.0
 */
@Controller
public class LoginController {
	//登录
	@RequestMapping("/login")
	public String login(HttpSession session,String username, String password) throws Exception{
		//调用service进行验证
		
		//在session中保存用户身份信息
		session.setAttribute("username", username);
		
		return "redirect:/items/queryItems.action";
	}
	
	//退出
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception{
		//清除session
		session.invalidate();
		
		//重定向到登录
		return "redirect:";
	}
}
