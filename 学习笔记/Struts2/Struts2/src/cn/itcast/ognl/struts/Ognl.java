package cn.itcast.ognl.struts;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

public class Ognl {
	private String stringInAction = "stringInAction";
	private List<Book> books = new ArrayList<Book>();

	public List<Book> getBooks() {
		return books;
	}

	public String getStringInAction() {
		return stringInAction;
	}

	public String execute() {
		ActionContext acx = ActionContext.getContext();
		acx.put("stirngInRequest", "stirngInRequest");
		acx.getApplication().put("stirngInApplication", "stirngInApplication");
		acx.getSession().put("stirngInSession", "stirngInSession");
		books.add(new Book(1, "book1", 100.0));
		books.add(new Book(2, "book2", 98.0));
		books.add(new Book(3, "book3", 99.0));
		return "ognl";
	}
}
