package cn.hibernate_homework.controller;

import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;
import cn.hibernate_homework.model.PrivillegeModel;

public class PrivillegeController {
	public static void main(String[] args) {

	}

	public void printPrivillege(Privillege privillege) {
		if (null == privillege) {
			System.out.println("null");
			return;
		}
		String str = "[Employee:id=" + privillege.getId() + " action="
				+ privillege.getAction() + " accounts="
				+ privillege.getAccounts() + "]";
		System.out.println(str);
	}

	public void add(Privillege newPrivillege) {
		Privillege pvl = new PrivillegeModel().add(newPrivillege);
		System.out.println(pvl);
	}

	public void addToUserAccount(Privillege privillege, UserAccount userAccount) {
		privillege = new PrivillegeModel().addToUserAccount(privillege,
				userAccount);
		printPrivillege(privillege);
	}

	public void queryEmployeesOfOnePrivillege(Privillege privillege) {
		System.out.println(new PrivillegeModel()
				.queryEmployeesOfOnePrivillege(privillege));
	}
}
