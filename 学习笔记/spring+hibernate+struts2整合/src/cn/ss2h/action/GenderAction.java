/**
 *
 */
package cn.ss2h.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.ss2h.domain.Gender;
import cn.ss2h.service.GenderService;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
@Controller("genderAction")
@Scope("prototype")
public class GenderAction {
	@Resource(name = "genderService")
	private GenderService genderService;

	public String getAll() {
		List<Gender> genders = genderService.getAllGenders();
		JSONObject json = new JSONObject();
		for(int i = 0; i < genders.size(); ++i){
			JSONObject jsonItem = new JSONObject();
			jsonItem.accumulate("id", genders.get(i).getId());
			jsonItem.accumulate("name", genders.get(i).getName());
			json.accumulate(i+"", jsonItem);
		}
		ActionContext.getContext().put("json",
				json.toString());
		return "json";
	}

	public String getAllToHTML() {
		List<Gender> genders = genderService.getAllGenders();
		String gendershtml = new String();
		for (Gender gender : genders) {
			gendershtml += "<label><input type='radio' value='"
					+ gender.getId() + "'>" + gender.getName() + "</label>";
		}
		ActionContext.getContext().put("genders", gendershtml);
		return "genderJson";
	}
}
