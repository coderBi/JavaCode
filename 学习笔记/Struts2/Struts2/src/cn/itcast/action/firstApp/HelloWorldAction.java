package cn.itcast.action.firstApp;

import java.io.UnsupportedEncodingException;

public class HelloWorldAction {
	private String message;
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws UnsupportedEncodingException {
		// name = URLDecoder.decode(name,"utf-8");
		// System.out.println("name getbytes:" + name.getBytes("ISO-8859-1"));
		// System.out.println("name utf-8:" + new
		// String(name.getBytes("ISO-8859-1"), "utf-8"));
		// System.out.println("name gbk:" + new
		// String(name.getBytes("ISO-8859-1"), "gbk"));
		// System.out.println("name ISO-8859-1:" + new
		// String(name.getBytes("ISO-8859-1"), "ISO-8859-1"));
		// System.out.println("name:" + name);
		
		//为了应对可能是get提交过来的中文，这里我把server.xml里面的8080端口设置过了URIEncoding="utf-8"
		this.name = name;
	}

	// struts里面规定action的返回是String
	public String execute() {
		// message = "message";
		return "success";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
