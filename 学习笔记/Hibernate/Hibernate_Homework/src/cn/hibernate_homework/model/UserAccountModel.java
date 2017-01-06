package cn.hibernate_homework.model;

import java.util.Set;

import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;

public class UserAccountModel extends ModelModule {
	public void printUserAccount(UserAccount userAccount) {
		if (null == userAccount) {
			System.out.println("null");
			return;
		}
		String str = "[Employee:id=" + userAccount.getId() + " loginName="
				+ userAccount.getLoginName() + " password="
				+ userAccount.getPassword() + " employee="
				+ userAccount.getEmployee() + " desc="
				+ userAccount.getPrivillege() + "]";
		System.out.println(str);
	}

	public UserAccount add(UserAccount newUserAccount) {
		return super.add(newUserAccount);
	}

	public boolean isPersistent(UserAccount userAccount) {
		if (null == userAccount) {
			return false;
		}
		return super.isPersistent(UserAccount.class, userAccount,
				userAccount.getId());
	}

	public UserAccount updatePassowrd(UserAccount userAccount, String password) {
		if (userAccount == null) {
			throw new RuntimeException("UserAccount对象为空");
		}
		if (null == (userAccount = findById(userAccount.getId()))) {
			throw new RuntimeException("UserAccount对象在数据库中不存在");
		}
		userAccount.setPassword(password);
		return userAccount;
	}

	public UserAccount findById(Long id) {
		return super.findById(UserAccount.class, id);
	}

	public boolean delete(UserAccount userAccount) {
		if (null == userAccount
				|| null == (userAccount = findById(userAccount.getId()))) {
			throw new RuntimeException("UserAccount对象在数据库中不存在");
		}
		session.delete(userAccount);
		return true;
	}

	public void resetEmployeePassword(String newPassword) {
		String hql = "update UserAccount as a set a.password=:newPassword where a.employee!=null";
		session.createQuery(hql).setParameter("newPassword", newPassword)
				.executeUpdate();
	}

	public UserAccount addPrivillegeToUserAccount(UserAccount userAccount,
			Privillege... privilleges) {
		if (!isPersistent(userAccount)) {
			throw new RuntimeException("userAccount在数据库中不存在");
		}
		PrivillegeModel pm = new PrivillegeModel();
		Set<Privillege> privillegeList = userAccount.getPrivillege();
		for (int i = 0; i < privilleges.length; ++i) {
			if (!pm.isPersistent(privilleges[i])) {
				throw new RuntimeException("privilleges数组中index为" + i
						+ "的在数据库中不存在");
			}
			privillegeList.add(privilleges[i]);
		}
		printUserAccount(userAccount);
		return userAccount;
	}
	
	public UserAccount removePrivillegeFromAccount(UserAccount userAccount,Privillege...privilleges){
		if(!new UserAccountModel().isPersistent(userAccount)){
			throw new RuntimeException("userAccount在数据库中不存在");
		}
		Set<Privillege> privillegeSet = userAccount.getPrivillege();
		for(Privillege privillege : privilleges){
			if(privillegeSet.contains(privillege)){
				privillegeSet.remove(privillege);
			}
		}
		return userAccount;
	}
	
	public boolean checkPrivillegeFromUserAccount(UserAccount userAccount,
			Privillege... privilleges){
		if(!new UserAccountModel().isPersistent(userAccount)){
			throw new RuntimeException("userAccount在数据库中不存在");
		}
		Set<Privillege> privillegeSet = userAccount.getPrivillege();
		for(Privillege privillege : privilleges){
			if(!privillegeSet.contains(privillege)){
				return false;
			}
		}
		return true;
	}
	
	public Set<Privillege> getPrivilleges(UserAccount userAccount){
		if(!new UserAccountModel().isPersistent(userAccount)){
			throw new RuntimeException("userAccount在数据库中不存在");
		}
		return userAccount.getPrivillege();
	}
}
