package cn.itcast.Model;

import cn.itcast.domain.PageResultSet;
import cn.itcast.domain.User;

public class UserModel extends ModelModule {
	public User add(User user) {
		return super.add(user);
	}

	public User update(User user) {
		if(null == find(user.getId()))
			return null;
		return super.update(user);
	}
	
	public User delete(User user) {
		if(null == find(user.getId()))
			return null;
		return super.delete(user);
	}
	
	public User deleteById(int id){
		if(null == find(id))
			return null;
		return super.deleteById(User.class, id);
	}
	
	public User find(int id) {
		return super.find(User.class, id);
	}
	
	public PageResultSet findAll(int pageNum) {
		pageNum = pageNum < 1 ? 1 : pageNum;  //pageNum约定要至少是1
		return  super.findAll(User.class, pageNum);
	}
}
