package cn.itcast.service.impl;

import cn.itcast.dao.Person;
import cn.itcast.myContext.myAnnotation;
import cn.itcast.service.PersonService;

public class PersonServiceBean implements PersonService {
	@myAnnotation(name="person") private Person person;
	private String value;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 在实例化对象之后，进行的初始化配置。
	 */
	public void init() {
		System.out.println("PersonServiceBean对象的init方法");
	}

	public PersonServiceBean() {
		System.out.println("PersonServiceBean构造函数执行");
	}

	public PersonServiceBean(Person person){
		this.person = person;
	}
	
	public void destroy() {
		System.out.println("PersonServiceBean对象的destroy方法");
	}

	public void save() {
		System.out.println("save method");
	}

	public void add() {
		person.add();
	}
	
	public void printValue() {
		System.out.println("value "+value);
	}
	
	public void printValue(String value){
		this.value = value;
		System.out.println("value "+value);
	}
}
