package cn.itcast.action.typeConvert;

public class DateTypeConvert {
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String execute(){
		return "date";
	}
}
