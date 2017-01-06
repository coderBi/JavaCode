/**
 *
 */
package mybatis.dao.mapper;

import java.util.List;

import mybatis.pojo.Person;
import mybatis.pojo.PersonCustom;
import mybatis.pojo.PersonQueryVo;

/**
 * <p>Title:UserMapper</p>
 * <p>Description:user接口</p>
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public interface UserMapper {
	//方法跟类的签名符合约定，mybatis自动生成这个接口的实现对象。在调用的地方通过sqlSession.getMapper(UserMapper.class)获得相应的对象。
	public Person findById(Integer id) throws Exception;
	
	public List<PersonCustom> findUserList(PersonQueryVo personQueryVo) throws Exception;
	
	public List<Person> selectUserOrders() throws Exception;
}
