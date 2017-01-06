package cn.hibernate_homework.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.hibernate_homework.bean.Department;
import cn.hibernate_homework.bean.Employee;

public class DepartmentModel extends ModelModule {
	public Department add(Department newDepartment) {
		return super.add(newDepartment);
	}

	public Department changeName(Department dp, String name) throws Throwable {
		if (dp.getId() == null) {
			throw new Throwable("所提供的Department的id为空");
		}

		Department getReturn = null;
		if ((getReturn = findById(dp.getId())) == null) {
			throw new Throwable("所提供的Department对象在数据库没找到");
		}
		getReturn.setName(name);
		return getReturn;
	}

	public Department findById(Long id) {
		return super.findById(Department.class, id);
		// return (Department) session.get(Department.class, id);
	}

	public List<Department> findTopLevelDepartment() {
		@SuppressWarnings("unchecked")
		List<Department> res = (List<Department>) session.createQuery(
				"from Department as d where d.parent=null order by d.id asc")
				.list();
		return res;
	}

	public Department addEmployee(Department department, Employee employee) {
		if (null == department) {
			throw new RuntimeException("参数department is null");
		}
		if (null == employee) {
			throw new RuntimeException("参数employee is null");
		}
		if (null != (employee = (Employee) session.get(Employee.class,
				(Serializable) employee.getId()))) {
			Set<Employee> employess = department.getEmployees() != null ? department
					.getEmployees() : new LinkedHashSet<Employee>();
			employess.add(employee);
		} else {
			throw new RuntimeException("参数employee 在数据库中不存在");
		}
		return department;
	}

	public Department removeAllEmployees(Department department) {
		if (null == department) {
			return null;
		}
		department.setEmployees(null);
		return department;
	}

	public boolean delete(Department department) throws Throwable {
		if (null == department) {
			return true;
		}
		if (null == (department = findById(department.getId()))) {
			throw new Throwable("department在数据库中不存在");
		}
		if (null != department.getEmployees()
				&& department.getEmployees().size() != 0) {
			throw new Throwable("部门里面有员工,不能删除");
		}
		Set<Department> children = null;
		if (null != (children = department.getChildren())) {
			for (Department dp : children) {
				delete(dp);
			}
		}
		session.delete(department);
		return true;
	}

	public List<Department> getAllChildrenById(Long id) {
		String hql = "select d from Department as d where d.parent=?";
		@SuppressWarnings("unchecked")
		List<Department> children = session.createQuery(hql).setLong(0, id)
				.list();
		return children;
	}

	public Long getDepartmentCount() {
		return super.getCount(Department.class);
	}

	public class EmployeeCountInEveryDepartmentResult {
		private Long departmentId;
		private Long count;

		public Long getId() {
			return departmentId;
		}

		public EmployeeCountInEveryDepartmentResult() {
			super();
		}

		public EmployeeCountInEveryDepartmentResult(Long departmentId,
				Long count) {
			super();
			this.departmentId = departmentId;
			this.count = count;
		}

		public void setId(Long departmentId) {
			this.departmentId = departmentId;
		}

		public Long getCount() {
			return count;
		}

		public void setCount(Long count) {
			this.count = count;
		}

		@Override
		public String toString() {
			return "[DepartmentModel.EmployeeCountInEveryDepartmentResult:"
					+ "departmentId=" + departmentId + " count=" + count + "]";
		}
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeCountInEveryDepartmentResult> getEmployeeCountInEveryDepartment() {
		String hql = "select d.id,count(e.id) from Department as d left outer join d.employees as e"
				+ " group by d.id order by d.id asc";
		List<EmployeeCountInEveryDepartmentResult> result = new ArrayList<EmployeeCountInEveryDepartmentResult>();
		Iterator<Object[]> it = session.createQuery(hql).iterate();
		while(it.hasNext()){
			Object[] row = it.next();
			result.add(new EmployeeCountInEveryDepartmentResult((Long)row[0],(Long)row[1]));
		}
		return result;
	}
}
