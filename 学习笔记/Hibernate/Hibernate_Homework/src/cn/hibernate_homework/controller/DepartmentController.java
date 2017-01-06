package cn.hibernate_homework.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.hibernate_homework.bean.Department;
import cn.hibernate_homework.bean.Employee;
import cn.hibernate_homework.model.DepartmentModel;
import cn.hibernate_homework.model.EmployeeModel;
import cn.hibernate_homework.utils.SessionFactoryUtils;

public class DepartmentController {
	public static void main(String[] args) {
		SessionFactory sf = SessionFactoryUtils.getSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		System.out.println(session);
		session.getTransaction().commit();
		System.out.println(session);
	}

	private void printDeparment(Department dp) {
		System.out.println("[Department:id=" + dp.getId() + " name="
				+ dp.getName() + " parent=" + dp.getParent() + " children="
				+ dp.getChildren() + " employees=" + dp.getEmployees() + "]");
	}

	public void add(Department newDepartment) {
		Department dp = new DepartmentModel().add(newDepartment);
		printDeparment(dp);
	}

	public void changeName(Department dp, String name) {
		DepartmentModel dpm = new DepartmentModel();
		Department returnDp;
		try {
			returnDp = dpm.changeName(dp, name);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if (returnDp != null) {
			System.out.println("修改成功，修改后内容：" + returnDp.getName());
		} else {
			System.out.println("修改失败");
		}
	}

	public void addEmployee(Department department, Employee employee) {
		Department dp = new DepartmentModel().addEmployee(department, employee);
		printDeparment(dp);
	}

	public void removeAllEmployees(Department department) {
		department = new DepartmentModel().removeAllEmployees(department);
		printDeparment(department);
	}

	public void delete(Department department) {
		boolean result = false;
		try {
			result = new DepartmentModel().delete(department);
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("删除一个部门失败,具体的原因是:" + e.getLocalizedMessage());
		}
		if (true == result) {
			System.out.println("已经成功删除一个部门");
			department = null;
		}
	}

	public void getAllChildrenById(Long id) {
		List<Department> children = new DepartmentModel()
				.getAllChildrenById(id);
		System.out.println(children);
	}

	public void getEmpCountDepCountAndCountInGroup() {
		Long empCount = new EmployeeModel().getEmployeeCount();
		DepartmentModel dm = new DepartmentModel();
		Long depCount = dm.getDepartmentCount();
		List<DepartmentModel.EmployeeCountInEveryDepartmentResult> counts = dm
				.getEmployeeCountInEveryDepartment();
		System.out.println("empCount=" + empCount + " depCount=" + depCount
				+ " counts=" + counts);
	}
}
