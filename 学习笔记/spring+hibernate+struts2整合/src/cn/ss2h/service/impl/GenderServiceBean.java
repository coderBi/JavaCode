/**
 *
 */
package cn.ss2h.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ss2h.domain.Gender;
import cn.ss2h.domain.Person;
import cn.ss2h.service.GenderService;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
@Transactional
@Service("genderService")
public class GenderServiceBean extends BaseServiceImpl implements GenderService {
	/**
	 * 获取一个session，通过向session工厂调用getCurrentSession。要注意的是这个函数由于用了注解sessionFactory
	 * ，而注解是在对象产生之后才进行的，所以不能再对象构造器里面取引用这个getCurrentSession。
	 * 
	 * @return 获取到的Session对象
	 */
	private Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Override
	public void add(Gender gender) {
		getSession().persist(gender);
	}

	@Override
	public void update(Gender gender) {
		getSession().merge(gender);
	}

	@Override
	public void delete(Gender... genders) {
		for (Gender gender : genders) {
			getSession().delete(gender);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Gender getGender(Integer id) {
		return (Gender) getSession().get(Gender.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Gender> getGenders(Integer... ids) {
		List<Gender> genders = new ArrayList<Gender>();
		for (Integer id : ids) {
			Gender gender = getGender(id);
			if (null != gender)
				genders.add(gender);
		}
		return genders;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Gender> getAllGenders() {
		String hql = "from Gender";
		return (List<Gender>) fetchAll(hql, new Object[] {});
	}

}
