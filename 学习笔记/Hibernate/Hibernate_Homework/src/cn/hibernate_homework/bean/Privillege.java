package cn.hibernate_homework.bean;

import java.util.Set;

public class Privillege {
	private Long id;
	private String action;
	private Set<UserAccount> accounts;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Set<UserAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<UserAccount> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "[Privillege:id="+id+" action="+action+"]";
	}
}
