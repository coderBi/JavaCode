/**
 *
 */
package ssm.mapper;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.po.Items;
import ssm.po.ItemsExample;

/**
 * <p>Title:ItemsMapperTest</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2017-2-25
 * @version 1.0
 */
public class ItemsMapperTest {
	private ApplicationContext applicationContext;
	private ItemsMapper itemsMapper;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		itemsMapper = (ItemsMapper) applicationContext.getBean("itemsMapper");
	}

	/**
	 * Test method for {@link ssm.mapper.ItemsMapper#insert(ssm.po.Items)}.
	 */
	@Test
	public void testInsert() {
		Items items = new Items();
		items.setCreatetime(null);
		items.setDetail("from ItemsMapperTest");
		items.setName("笔记本");
		items.setPrice(100f);
		itemsMapper.insert(items);
	}

	/**
	 * Test method for {@link ssm.mapper.ItemsMapper#selectByExample(ssm.po.ItemsExample)}.
	 */
	@Test
	public void testSelectByExample() {
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameEqualTo("商品2"); //查询条件 name=商品2
		List<Items> itemsList = itemsMapper.selectByExample(itemsExample);
		System.out.println(itemsList);
	}

	/**
	 * Test method for {@link ssm.mapper.ItemsMapper#selectByPrimaryKey(java.lang.Integer)}.
	 */
	@Test
	public void testSelectByPrimaryKey() {
		Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println(items);
	}

	/**
	 * Test method for {@link ssm.mapper.ItemsMapper#updateByPrimaryKeySelective(ssm.po.Items)}.
	 */
	@Test
	public void testUpdateByPrimaryKeySelective() {
		//这个方法跟testUpdateByPrimaryKey的区别是这个方法如果传入的字段是null就不进行更新。udpateByPrimaryKey一般是先查询，而updateByPrimaryKeySelective可以直接指定一个id跟一个要更改的属性就行
		Items items = new Items();
		items.setId(1);
		try {
			items.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-1-1 1:1:1"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		itemsMapper.updateByPrimaryKeySelective(items);
	}

	/**
	 * Test method for {@link ssm.mapper.ItemsMapper#updateByPrimaryKey(ssm.po.Items)}.
	 */
	@Test
	public void testUpdateByPrimaryKey() {
		Items items = itemsMapper.selectByPrimaryKey(1);
		items.setName("新名称");
		itemsMapper.updateByPrimaryKey(items);
	}

}
