/**
 *
 */
package cn.ss2h.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.ss2h.domain.Gender;
import cn.ss2h.domain.Person;
import cn.ss2h.service.GenderService;
import cn.ss2h.service.PersonService;

/**
 * 
 * <p>Title:PersonAction</p>
 * <p>Description:与Person操作相关的Action类</p>
 * @author Edwin
 * @since 2016-7-21
 * @version 1.0
 */
@Controller("personAction")
@Scope("prototype")
public class PersonAction extends ActionSupport {
	private static final long serialVersionUID = 2749711643032501147L;
	private Person person;
	@Resource(name = "personService")
	private PersonService personService;
	@Resource(name = "genderService")
	private GenderService genderService;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String list() {
		List<Person> persons = personService.getAllPersons();
		ActionContext.getContext().put("persons", persons);
		return "list";
	}

	public String add() {
		try {
			personService.add(person);
			ActionContext.getContext().put("message", "插入用户成功");
		} catch (Exception e) {
			ActionContext.getContext().put("message", "插入用户失败");
			e.printStackTrace();
		}
		return "add";
	}
	
	public String form() {
		ActionContext.getContext().put("person", person);
		List<Gender> genders = genderService.getAllGenders();
		ActionContext.getContext().put("genders", genders);
		return "form";
	}

	public String checkPersonName() {
		Long count = (Long) personService.fetchOne(
				"select count(*) from Person as p where p.name=?",
				person.getName());
		String message = count < 1 ? "noExist" : "exist";
		ActionContext.getContext().put("message", message);
		return "message";
	}

	public void validateAdd() {
		if (null == person) {
			addFieldError("person", "没有提供要添加的person对象");
			return;
		}
		if (null == person.getName() || "".equals(person.getName().trim())) {
			addFieldError("person", "用户名不能为空");
		} else {
			if (!"noExist".equals(checkPersonName1()))
				addFieldError("person", "用户名已存在");
		}
		if (null == person.getGender() || person.getGender().getId() == null) {
			addFieldError("person", "用户性别不能为空");
		}
	}
	/**
	 * 用作后台用户名是否存在检查
	 * @return 用户名存在，返回"exist", 不存在返回"noExist"
	 */
	private String checkPersonName1() {
		Long count = (Long) personService.fetchOne(
				"select count(*) from Person as p where p.name=?",
				person.getName());
		String message = count < 1 ? "noExist" : "exist";
		return message;
	}
}
