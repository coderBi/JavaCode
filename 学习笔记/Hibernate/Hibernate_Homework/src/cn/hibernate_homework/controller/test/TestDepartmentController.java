package cn.hibernate_homework.controller.test;

import org.hibernate.Session;
import org.junit.Test;

import cn.hibernate_homework.bean.Department;
import cn.hibernate_homework.bean.Employee;
import cn.hibernate_homework.controller.DepartmentController;
import cn.hibernate_homework.model.DepartmentModel;
import cn.hibernate_homework.model.EmployeeModel;
import cn.hibernate_homework.utils.SessionFactoryUtils;

public class TestDepartmentController {
	private DepartmentController dc = new DepartmentController();
	private static Session session = SessionFactoryUtils.getSessionFactory()
			.getCurrentSession();
	static {
		session.beginTransaction();
	}

	@Test
	public void testAdd() {
		Department newDepartment1 = new Department("testname", null, null, null);
		dc.add(newDepartment1);
		session.getTransaction().commit();
	}

	@Test
	public void testChangeName() {
		dc.changeName(new DepartmentModel().findById(1L), "changeName");
		session.getTransaction().commit();
	}

	@Test
	public void testQueryTopLevelDepartment() {
		System.out.println(new DepartmentModel().findTopLevelDepartment());
		session.getTransaction().commit();
	}
	
	@Test
	public void testAddEmployee(){
		Department department = new DepartmentModel().findById(2L);
		Employee employee = new EmployeeModel().findById(2L);
		dc.addEmployee(department, employee);
		session.getTransaction().commit();
	}
	
	@Test
	public void testRemoveAllEmployees() {
		Department department = new DepartmentModel().findById(3L);
		dc.removeAllEmployees(department);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDelete(){
		Department department = new DepartmentModel().findById(3L);
		dc.delete(department);
		session.getTransaction().commit();
	}
	
	@Test
	public void testGetAllChildrenById(){
		Long id = 1L;
		dc.getAllChildrenById(id);
	}
	@Test
	public void testGetEmpCountDepCountAndCountInGroup(){
		dc.getEmpCountDepCountAndCountInGroup();
	}
	
}
