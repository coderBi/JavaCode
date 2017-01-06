/**
 *
 */
package cn.itcast.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
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
@Controller(value="/person/manage") @Scope("prototype")
public class PersonManageAction extends DispatchAction {
	@Resource(name="personService")
	private PersonService personService;
	
	//这个方法如果被重写，其他的所有方法都不会起作用了。
//	@Override
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		return mapping.findForward("listForm");
//	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PersonForm formbean = (PersonForm)form;
		try {
			personService.save(new Person(formbean.getName()));
			request.setAttribute("message","添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","添加失败");
		}
		return mapping.findForward("message");
	}
	
	public ActionForward showForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("listForm");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("persons", personService.getPersons());
		return mapping.findForward("list");
	}
}
