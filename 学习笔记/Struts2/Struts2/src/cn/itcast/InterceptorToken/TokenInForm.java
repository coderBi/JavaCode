package cn.itcast.InterceptorToken;

public class TokenInForm {
	private String name;
	private Boolean reSubmit = true;
	
	public Boolean getReSubmit() {
		return reSubmit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String execute(){
		System.out.println("执行这次提交");
		reSubmit = false;
		return "tokenInForm";
	}
}
