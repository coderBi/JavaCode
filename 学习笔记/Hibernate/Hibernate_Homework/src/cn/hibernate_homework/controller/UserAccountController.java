package cn.hibernate_homework.controller;

import cn.hibernate_homework.bean.UserAccount;
import cn.hibernate_homework.model.UserAccountModel;

public class UserAccountController {
	public static void main(String[] args) {

	}

	public void printUserAccount(UserAccount userAccount) {
		String str = "[UserAccount::id=" + userAccount.getId() + " loginName="
				+ userAccount.getLoginName() + " password="
				+ userAccount.getPassword() + " employee="
				+ userAccount.getEmployee() + " privilleges="
				+ userAccount.getPrivillege() + "]";
		System.out.println(str);
	}

	public void add(UserAccount newUserAccount) {
		UserAccount dp = new UserAccountModel().add(newUserAccount);
		System.out.println(dp);
	}

	public void findById(Long id) {
		UserAccount ua = new UserAccountModel().findById(id);
		System.out.println(ua);
	}

	public void updatePassowrd(UserAccount userAccount, String password) {
		UserAccount ua = new UserAccountModel().updatePassowrd(userAccount,
				password);
		System.out.println(ua);
	}

}
