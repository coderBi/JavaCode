/**
 *
 */
package mybatis.pojo;

/**
 * <p>Title:PersonQueryVo</p>
 * <p>Description:包装的po，用于接收可能的视图层传入</p>
 * @author Edwin
 * @since 2016-7-31
 * @version 1.0
 */
public class PersonQueryVo {

	//用户查询条件
	private PersonCustom personCustom;

	public PersonCustom getPersonCustom() {
		return personCustom;
	}

	public void setPersonCustom(PersonCustom personCustom) {
		this.personCustom = personCustom;
	}
}
