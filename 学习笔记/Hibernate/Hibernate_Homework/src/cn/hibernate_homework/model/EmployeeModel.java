package cn.hibernate_homework.model;

import java.util.List;
import java.util.Set;

import cn.hibernate_homework.bean.Department;
import cn.hibernate_homework.bean.Employee;
import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;

public class EmployeeModel extends ModelModule {
	public Employee add(Employee newEmployee) {
		return super.add(newEmployee);
	}

	public Employee findById(Long id) {
		return super.findById(Employee.class, id);
	}

	public boolean isPersistent(Employee employee) {
		if (null == employee) {
			return false;
		}
		return super.isPersistent(Employee.class, employee, employee.getId());
	}

	public Employee updateDepartment(Employee employee, Department dp) {
		if (null == dp.getId()) {
			throw new RuntimeException("提供的Department对象id为空");
		}
		if ((dp = new DepartmentModel().findById(dp.getId())) == null) {
			throw new RuntimeException("提供的Department在数据库中不存在");
		}
		if (employee == null) {
			throw new RuntimeException("相应的employee不存在");
		}
		employee.setDepartment(dp);
		return employee;
	}

	public Long getEmployeeCount() {
		return super.getCount(Employee.class);
	}

	public Employee addAccountToEmployee(Employee employee, String loginName,
			String password) {
		UserAccount userAccount = new UserAccount();
		userAccount.setLoginName(loginName);
		userAccount.setPassword(password);
		session.save(userAccount);
		return addAccountToEmployee(employee, userAccount);
	}

	public Employee addAccountToEmployee(Employee employee,
			UserAccount userAccount) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("参数employee在数据库中不存在");
		}
		if (userAccount == null) {
			throw new RuntimeException("参数userAccount为空");
		}
		if (null == (userAccount = new UserAccountModel().findById(userAccount
				.getId()))) {
			throw new RuntimeException("参数userAccount在数据库中不存在");
		}
		employee.setAccount(userAccount); // 一对一，没有外键的这边不能维护关系
		userAccount.setEmployee(employee);
		return employee;
	}

	public Employee updateAccountPassword(Employee employee, String newPassword) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("参数employee在数据库中不存在");
		}
		UserAccount userAccount = null;
		if (null == (userAccount = employee.getAccount())) {
			throw new RuntimeException("employee还没有被分配账户");
		}
		userAccount.setPassword(newPassword);
		return employee;
	}

	public Employee deleteAccountFromEmployee(Employee employee) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("参数employee在数据库中不存在");
		}
		new UserAccountModel().delete(employee.getAccount());
		employee.setAccount(null);
		return employee;
	}

	public boolean delete(Employee employee) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("参数employee在数据库中不存在");
		}
		session.delete(employee);
		return true;
	}

	public boolean deleteEmployeeAndAccount(Employee employee) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("参数employee在数据库中不存在");
		}
		deleteAccountFromEmployee(employee);
		session.delete(employee);
		return true;
	}

	public Employee queryEmployeeByLoginNameAndPassword(String loginName,
			String password) {
		String hql = "select e from Employee as e inner join e.account as"
				+ " u where u.loginName=:loginName and u.password=:password";
		Employee employee = (Employee) session.createQuery(hql)
				.setParameter("loginName", loginName)
				.setParameter("password", password).uniqueResult();
		return employee;
	}

	public List<?> queryEmployeesWithAccount() {
		String hql = "select e from Employee as e left outer join e.account as a where a.id!=null";
		return session.createQuery(hql).list();
	}

	public void resetPassword(String newPassword) {
		new UserAccountModel().resetEmployeePassword(newPassword);
	}

	public Employee addPrivillegeToEmployee(Employee employee,
			Privillege... privilleges) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("employee参数在数据库中未找到");
		}
		UserAccount userAccount = null;
		if ((userAccount = employee.getAccount()) == null) {
			throw new RuntimeException("employee还没有被分配账户");
		}
		new UserAccountModel().addPrivillegeToUserAccount(userAccount,
				privilleges);
		return employee;
	}

	public Employee addPrivillegeToEmployee(Employee employee, Long... ids) {
		Privillege[] privilleges = new PrivillegeModel()
				.fromIdArray2PrivillegeArray(ids);
		return addPrivillegeToEmployee(employee, privilleges);
	}

	public Employee removePrivillegeFromEmployee(Employee employee,
			Privillege... privilleges) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("employee在数据库中不存在");
		}
		if (null == employee.getAccount())
			return employee;
		new UserAccountModel().removePrivillegeFromAccount(
				employee.getAccount(), privilleges);
		return employee;
	}

	public Employee removePrivillegeFromEmployee(Employee employee, Long... ids) {
		Privillege[] privilleges = new PrivillegeModel()
				.fromIdArray2PrivillegeArray(ids);
		return removePrivillegeFromEmployee(employee, privilleges);
	}

	public boolean checkPrivillegeFromEmployee(Employee employee, Long... ids) {
		Privillege[] privilleges = new PrivillegeModel()
				.fromIdArray2PrivillegeArray(ids);
		return checkPrivillegeFromEmployee(employee, privilleges);
	}

	public boolean checkPrivillegeFromEmployee(Employee employee,
			Privillege... privilleges) {
		if (!isPersistent(employee) || null == employee.getAccount()) {
			return false;
		}
		return new UserAccountModel().checkPrivillegeFromUserAccount(
				employee.getAccount(), privilleges);
	}
	
	public Set<Privillege> getPrivilleges(Employee employee){
		if (!isPersistent(employee) || null == employee.getAccount()) {
			return null;
		}
		return new UserAccountModel().getPrivilleges(
				employee.getAccount());
	}
}
