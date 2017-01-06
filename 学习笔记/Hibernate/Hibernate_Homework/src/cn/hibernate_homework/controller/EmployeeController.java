package cn.hibernate_homework.controller;

import java.util.List;
import java.util.Set;

import cn.hibernate_homework.bean.Department;
import cn.hibernate_homework.bean.Employee;
import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;
import cn.hibernate_homework.model.EmployeeModel;
import cn.hibernate_homework.model.PrivillegeModel;
import cn.hibernate_homework.model.UserAccountModel;

public class EmployeeController {
	public static void main(String[] args) {

	}

	private EmployeeModel employeeModel = new EmployeeModel();

	public void printEmployee(Employee employee) {
		if (null == employee) {
			System.out.println("null");
			return;
		}
		String str = "[Employee:id=" + employee.getId() + " name="
				+ employee.getName() + " gender=" + employee.isGender()
				+ " birthday=" + employee.getBirthday() + " desc="
				+ employee.getDesc() + " account=" + employee.getAccount()
				+ " " + "department=" + employee.getDepartment() + "]";
		System.out.println(str);
	}

	public void add(Employee newEmployee) {
		Employee em = new EmployeeModel().add(newEmployee);
		printEmployee(em);
	}

	public void updateDepartment(Employee employee, Department dp) {
		Employee em = new EmployeeModel().updateDepartment(employee, dp);
		printEmployee(em);
	}

	public void findById(Long id) {
		Employee em = new EmployeeModel().findById(id);
		printEmployee(em);
	}

	public void addAccountToEmployee(Employee employee, UserAccount userAccount) {
		Employee em = null;
		try {
			em = new EmployeeModel()
					.addAccountToEmployee(employee, userAccount);
		} catch (RuntimeException e) {
			System.out.println("给员工添加账户失败，具体原因是： " + e.getLocalizedMessage());
			throw e;
		}
		printEmployee(em);
	}

	public void addAccountToEmployee(Employee employee, String loginName,
			String password) {
		Employee em = null;
		try {
			em = new EmployeeModel().addAccountToEmployee(employee, loginName,
					password);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println("给员工添加账户失败，具体原因是： " + e.getLocalizedMessage());
			throw e;
		}
		printEmployee(em);
	}

	public void updateAccountPassword(Employee employee, String newPassword) {
		Employee em = null;
		try {
			em = new EmployeeModel().updateAccountPassword(employee,
					newPassword);
		} catch (RuntimeException e) {
			System.out.println("更新密码失败，具体的原因：" + e.getLocalizedMessage());
			throw e;
		}
		printEmployee(em);
	}

	public void deleteAccountFromEmployee(Employee employee) {
		Employee em = null;
		try {
			em = new EmployeeModel().deleteAccountFromEmployee(employee);
		} catch (RuntimeException e) {
			System.out.println("删除员工账户失败，具体的原因：" + e.getLocalizedMessage());
			throw e;
		}
		printEmployee(em);
	}

	public void deleteEmployeeAndAccount(Employee employee) {
		try {
			new EmployeeModel().deleteEmployeeAndAccount(employee);
		} catch (RuntimeException e) {
			System.out.println("删除员工及其账户失败，具体的原因：" + e.getLocalizedMessage());
			throw e;
		}
		System.out.println("删除员工及其账户成功");
	}

	public void queryEmployeeByLoginNameAndPassword(String loginName,
			String password) {
		Employee employee = employeeModel.queryEmployeeByLoginNameAndPassword(
				loginName, password);
		printEmployee(employee);
	}

	public void queryEmployeesWithAccount() {
		List<?> employees = employeeModel.queryEmployeesWithAccount();
		System.out.println(employees);
	}

	public void resetPassword(String newPassword) {
		employeeModel.resetPassword(newPassword);
	}

	public void addPrivillegeToEmployee(Employee employee, Long... ids) {
		employee = employeeModel.addPrivillegeToEmployee(employee, ids);
		printEmployee(employee);
	}

	public void removePrivillegeFromEmployee(Employee employee,
			Privillege... privilleges) {
		printEmployee(employeeModel.removePrivillegeFromEmployee(employee,
				privilleges));
	}

	public void removePrivillegeFromEmployee(Employee employee, Long... ids) {
		printEmployee(employeeModel.removePrivillegeFromEmployee(employee, ids));
	}

	public void checkPrivillegeFromEmployee(Employee employee, Long... ids) {
		boolean result = employeeModel.checkPrivillegeFromEmployee(employee,
				ids);
		String toPrint = null;
		if (result) {
			toPrint = "用户拥有相应权限";
		} else {
			toPrint = "用户没有相应的权限";
		}
		System.out.println(toPrint);
	}

	public void checkPrivillegeFromEmployee(Employee employee,
			Privillege... privilleges) {
		boolean result = employeeModel.checkPrivillegeFromEmployee(employee,
				privilleges);
		String toPrint = null;
		if (result) {
			toPrint = "用户拥有相应权限";
		} else {
			toPrint = "用户没有相应的权限";
		}
		System.out.println(toPrint);
	}

	public void getPrivilleges(Employee employee) {
		Set<Privillege> privilleges = employeeModel.getPrivilleges(employee);
		System.out.println(privilleges);
	}
}
