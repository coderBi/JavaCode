package cn.hibernate_homework.bean;

import java.util.Set;

public class Department {
	private Long id;
	private String name;
	private Set<Employee> employees;
	private Department parent;
	private Set<Department> children;
	
	public Department() {
		super();
	}

	public Department(String name, Set<Employee> employees,
			Department parent, Set<Department> children) {
		super();
		this.name = name;
		this.employees = employees;
		this.parent = parent;
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "[Department:id=" + id + " name=" + name + "]";
		//return "[Department:id=" + id + " name=" + name + "]";
	}
}
