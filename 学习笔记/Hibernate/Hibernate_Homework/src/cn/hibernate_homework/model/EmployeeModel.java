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
			throw new RuntimeException("�ṩ��Department����idΪ��");
		}
		if ((dp = new DepartmentModel().findById(dp.getId())) == null) {
			throw new RuntimeException("�ṩ��Department�����ݿ��в�����");
		}
		if (employee == null) {
			throw new RuntimeException("��Ӧ��employee������");
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
			throw new RuntimeException("����employee�����ݿ��в�����");
		}
		if (userAccount == null) {
			throw new RuntimeException("����userAccountΪ��");
		}
		if (null == (userAccount = new UserAccountModel().findById(userAccount
				.getId()))) {
			throw new RuntimeException("����userAccount�����ݿ��в�����");
		}
		employee.setAccount(userAccount); // һ��һ��û���������߲���ά����ϵ
		userAccount.setEmployee(employee);
		return employee;
	}

	public Employee updateAccountPassword(Employee employee, String newPassword) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("����employee�����ݿ��в�����");
		}
		UserAccount userAccount = null;
		if (null == (userAccount = employee.getAccount())) {
			throw new RuntimeException("employee��û�б������˻�");
		}
		userAccount.setPassword(newPassword);
		return employee;
	}

	public Employee deleteAccountFromEmployee(Employee employee) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("����employee�����ݿ��в�����");
		}
		new UserAccountModel().delete(employee.getAccount());
		employee.setAccount(null);
		return employee;
	}

	public boolean delete(Employee employee) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("����employee�����ݿ��в�����");
		}
		session.delete(employee);
		return true;
	}

	public boolean deleteEmployeeAndAccount(Employee employee) {
		if (!isPersistent(employee)) {
			throw new RuntimeException("����employee�����ݿ��в�����");
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
			throw new RuntimeException("employee���������ݿ���δ�ҵ�");
		}
		UserAccount userAccount = null;
		if ((userAccount = employee.getAccount()) == null) {
			throw new RuntimeException("employee��û�б������˻�");
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
			throw new RuntimeException("employee�����ݿ��в�����");
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
