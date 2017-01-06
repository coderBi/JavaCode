package cn.hibernate_homework.model;

import java.util.List;

import cn.hibernate_homework.bean.Employee;
import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;

public class PrivillegeModel extends ModelModule {
	public boolean isPersistent(Privillege privillege) {
		if (null == privillege) {
			return false;
		}
		return super.isPersistent(Privillege.class, privillege,
				privillege.getId());
	}

	public Privillege add(Privillege newPrivillege) {
		return super.add(newPrivillege);
	}

	public Privillege findById(Long id) {
		return super.findById(Privillege.class, id);
	}

	public Privillege addToUserAccount(Privillege privillege,
			UserAccount userAccount) {
		if (!isPersistent(privillege)) {
			throw new RuntimeException("privillege在数据库中不存在");
		}
		if (!new UserAccountModel().isPersistent(userAccount)) {
			throw new RuntimeException("userAccount在数据库中不存在");
		}
		privillege.getAccounts().add(userAccount);
		return privillege;
	}

	public Privillege[] fromIdArray2PrivillegeArray(Long ids[]) {
		PrivillegeModel pm = new PrivillegeModel();
		Privillege[] privilleges = new Privillege[ids.length];
		for (int i = 0; i < privilleges.length; ++i) {
			Privillege privillege = pm.findById(ids[i]);
			if (null == privillege) {
				throw new RuntimeException("参数ids中index为" + i + "是无效的权限id");
			}
			privilleges[i] = privillege;
		}
		return privilleges;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> queryEmployeesOfOnePrivillege(Privillege privillege) {
		if (!isPersistent(privillege)) {
			throw new RuntimeException("privillege对应的权限在数据库中不存在");
		}
		List<Employee> employees = null;
		String hql = "select e from Employee as e join e.account as a join "
				+ "a.privillege as p where p.id=?";
		employees = session.createQuery(hql)//
				.setParameter(0, privillege.getId())//
				.list();
		return employees;
	}
}
