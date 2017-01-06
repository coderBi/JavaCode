package cn.itcast.action.interceptor;

public class TestInterceptor {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String notUseInterceptor(){
		message = "不使用拦截器";
		return "showMessage";
	}
	
	public String useInterceptor(){
		message = "使用了拦截器";
		return "showMessage";
	}
}
