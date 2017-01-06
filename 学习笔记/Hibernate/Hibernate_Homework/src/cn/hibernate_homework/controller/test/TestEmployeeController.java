package cn.hibernate_homework.controller.test;

import java.sql.Date;

import org.hibernate.Session;
import org.junit.Test;

import cn.hibernate_homework.bean.Department;
import cn.hibernate_homework.bean.Employee;
import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;
import cn.hibernate_homework.controller.EmployeeController;
import cn.hibernate_homework.model.DepartmentModel;
import cn.hibernate_homework.model.EmployeeModel;
import cn.hibernate_homework.model.UserAccountModel;
import cn.hibernate_homework.utils.SessionFactoryUtils;

public class TestEmployeeController {
	private Session session = SessionFactoryUtils.getSessionFactory()
			.getCurrentSession();
	private EmployeeController ec = new EmployeeController();
	
	{
		session.beginTransaction();
	}
	
	@Test
	public void testAdd(){
		Employee emp = new Employee();
		emp.setBirthday(new Date(new java.util.Date().getTime()));
		emp.setDesc("新员工");
		emp.setGender(true);
		emp.setName("张三");
		ec.add(emp);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdateDepartment(){
		Department dp = new DepartmentModel().findById(1L);
		Employee em = new EmployeeModel().findById(1L);
		ec.updateDepartment(em, dp);
		session.getTransaction().commit();
	}
	
	@Test
	public void testFindById(){
		ec.findById(2L);
	}
	
	@Test
	public void testAddAccountToEmployee(){
		Employee employee = new EmployeeModel().findById(2L);
		UserAccount userAccount = new UserAccountModel().findById(1L);
		ec.addAccountToEmployee(employee, userAccount);
		session.getTransaction().commit();
	}
	
	@Test
	public void testAddAccountToEmployee1(){
		Employee employee = new EmployeeModel().findById(1L);
		String loginName = "addname";
		String password = "addpassword";
		ec.addAccountToEmployee(employee, loginName,password);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdateAccountPassword(){
		Employee employee = new EmployeeModel().findById(2L);
		String newPassword = "newpassword";
		ec.updateAccountPassword(employee, newPassword);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDeleteAccountFromEmployee(){
		Employee employee = new EmployeeModel().findById(2L);
		ec.deleteAccountFromEmployee(employee);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDeleteEmployeeAndAccount(){
		Employee employee = new EmployeeModel().findById(5L);
		ec.deleteEmployeeAndAccount(employee);
		session.getTransaction().commit();
	}
	
	@Test
	public void testQueryEmployeeByLoginNameAndPassword(){
		String loginName = "张三";//"addname";
		String password ="123";//"addpassword";
		ec.queryEmployeeByLoginNameAndPassword(loginName, password);
		session.getTransaction().commit();
	}
	@Test
	public void testQueryEmployeesWithAccount(){
		ec.queryEmployeesWithAccount();
		session.getTransaction().commit();
	}
	
	@Test
	public void testResetPassword(){
		String newPassword = "1234";
		ec.resetPassword(newPassword);
		session.getTransaction().commit();
	}
	
	@Test 
	public void testAddPrivillegeToEmployee(){
		Employee employee = new EmployeeModel().findById(1L);
		Long ids[] = {8L,9L,10L};
		ec.addPrivillegeToEmployee(employee, ids);
		session.getTransaction().commit();
	}
	
	@Test
	public void testTemovePrivillegeFromEmployee(){
		Employee employee = new EmployeeModel().findById(1L);
		Long ids = 10L;
		ec.removePrivillegeFromEmployee(employee, ids);
		session.getTransaction().commit();
	}
	
	@Test
	public void checkPrivillegeFromEmployee(){
		Employee employee = new EmployeeModel().findById(1L);
		Long ids = 8L;
		ec.checkPrivillegeFromEmployee(employee, ids);
	}
	
	@Test
	public void getPrivilleges(){
		Employee employee = new EmployeeModel().findById(3L);
		ec.getPrivilleges(employee);
	}
}
