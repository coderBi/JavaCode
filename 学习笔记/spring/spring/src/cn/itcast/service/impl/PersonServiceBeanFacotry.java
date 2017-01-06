package cn.itcast.service.impl;

public class PersonServiceBeanFacotry {
	public static PersonServiceBean createPersonServiceBean(){
		return new PersonServiceBean();
	}
	public PersonServiceBean createPersonServiceBean1(){
		return new PersonServiceBean();
	}
}
