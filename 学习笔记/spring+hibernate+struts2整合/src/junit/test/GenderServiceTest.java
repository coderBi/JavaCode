/**
 *
 */
package junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ss2h.domain.Gender;
import cn.ss2h.domain.Person;
import cn.ss2h.service.GenderService;
import cn.ss2h.service.PersonService;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
public class GenderServiceTest {
	static GenderService genderService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		genderService = (GenderService) new ClassPathXmlApplicationContext(
				"beans.xml").getBean("genderService");
	}
	/**
	 * Test method for {@link cn.ss2h.service.GenderService#add(cn.ss2h.domain.Gender)}.
	 */
	@Test
	public void testAdd() {
		genderService.add(new Gender("女"));
	}

	/**
	 * Test method for {@link cn.ss2h.service.GenderService#update(cn.ss2h.domain.Gender)}.
	 */
	@Test
	public void testUpdate() {
		Gender gender = genderService.getGender(2);
		gender.setName("id为2的新名称");
		genderService.update(gender);
		System.out.println(genderService.getGender(2).getName());
	}

	/**
	 * Test method for {@link cn.ss2h.service.GenderService#delete(cn.ss2h.domain.Gender[])}.
	 */
	@Test
	public void testDelete() {
		Gender gender = genderService.getGender(2);
		genderService.delete(gender);
	}

	/**
	 * Test method for {@link cn.ss2h.service.GenderService#getGender(java.lang.Integer)}.
	 */
	@Test
	public void testGetGender() {
		Gender gender = genderService.getGender(1);
		System.out.println(gender.getName());
	}

	/**
	 * Test method for {@link cn.ss2h.service.GenderService#getGenders(java.lang.Integer[])}.
	 */
	@Test
	public void testGetGenders() {
		List<Gender> genders = genderService.getGenders(1, 2, 3, 4);
		for (Gender gender : genders) {
			System.out.println(gender.getName());
		}
	}

	/**
	 * Test method for {@link cn.ss2h.service.GenderService#getAllGenders()}.
	 */
	@Test
	public void testGetAllGenders() {
		List<Gender> genders = genderService.getAllGenders();
		for(Gender gender : genders){
			System.out.println(gender.getName());
		}
	}

}
