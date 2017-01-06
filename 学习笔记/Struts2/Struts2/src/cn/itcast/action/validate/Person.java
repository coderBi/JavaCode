package cn.itcast.action.validate;

import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionSupport;

public class Person extends ActionSupport {
	private static final long serialVersionUID = -3359574900880524480L;
	private String name;
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String showForm() {
		return "personForm";
	}

	public String submit() {
		return "personForm";
	}

//	public void validateSubmit() { // 这样取名就是对某一个特定的方法进行校验
//		String mobileRegex = "^1[358]\\d{9}$";
//		if (name == null || "".equals(name.trim())) {
//			addFieldError("name", "name为空");
//		}
//		if (mobile == null || "".equals(mobile.trim())) {
//			addFieldError("moblie", "moblie为空");
//		} else if (Pattern.compile(mobileRegex).matcher(mobile.trim())
//				.matches() == false) {
//			addFieldError("moblie", "手机号格式不正确");
//		}
//	}

	// @Override
	// public void validate() { //validate会对这个类里面的所有请求进行校验
	// String mobileRegex = "^1[358]\\d{9}$";
	// if(name == null || "".equals(name.trim())){
	// addFieldError("name", "name为空");
	// }
	// if(mobile == null || "".equals(mobile.trim())){
	// addFieldError("moblie", "moblie为空");
	// } else if(Pattern.compile(mobileRegex).matcher(mobile.trim()).matches()
	// == false){
	// addFieldError("moblie", "手机号格式不正确");
	// }
	// }
}
