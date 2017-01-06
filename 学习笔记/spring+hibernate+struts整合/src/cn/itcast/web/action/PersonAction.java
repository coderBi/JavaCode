/**
 *
 */
package cn.itcast.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bean.Person;
import cn.itcast.service.PersonService;
import cn.itcast.web.formbean.PersonForm;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-20
 */
/* 将bean命名为跟路径一致，这样Struts得到这个路径向spring容器请求对象。 */
@Controller(value="/person/list") @Scope("prototype")
public class PersonAction extends Action {
	@Resource(name="personService")
	private PersonService personService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 这段代码跟struts api紧耦合，如果将这个action类也交给Spring管理，就可以对personService进行注解。
//		 WebApplicationContext webApplicationContext =
//		 WebApplicationContextUtils
//		 .getWebApplicationContext(this.getServlet().getServletContext());
//		 PersonService personService = (PersonService) webApplicationContext
//		 .getBean("personService");
		request.setAttribute("persons", personService.getPersons()); // 将persons放入request范围
		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm formbean = (PersonForm)form;
		personService.save(new Person(formbean.getName()));
		request.setAttribute("message","添加成功");
		return mapping.findForward("list");
	}
	
	public ActionForward listForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("list");
	}
}
