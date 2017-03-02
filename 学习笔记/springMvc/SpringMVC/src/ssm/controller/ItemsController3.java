/**
 *
 */
package ssm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ssm.po.Items;

/**
 * <p>
 * Title:Items
 * </p>
 * <p>
 * Description:使用注解的处理器
 * </p>
 * 
 * @author Edwin
 * @since 2017-2-21
 * @version 1.0
 */
@Controller
public class ItemsController3 {

	@RequestMapping("/queryItems4")
	public ModelAndView queryItems4() throws Exception {
		// 调用service查找数据库，查询商品列表
		List<Items> itemsList = new ArrayList<Items>();

		// 向list中填充静态数据
		Items item_1 = new Items();
		item_1.setId(1);
		item_1.setName("笔记本");
		item_1.setPrice(100f);
		item_1.setDetail("一个笔记本而已");
		item_1.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2011-01-01 00:00:00"));

		Items item_2 = new Items();
		item_2.setId(2);
		item_2.setName("苹果");
		item_2.setPrice(5000f);
		item_2.setDetail("这是一个很贵的苹果");
		item_2.setCreatetime(new SimpleDateFormat("yyyy-MM-dd")
				.parse("2111-01-01 00:00:00"));

		itemsList.add(item_1);
		itemsList.add(item_2);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList"); // 在springmvc.xml中配置过前缀
														// "/WEB-INF/jsp/"
														// 后缀".jsp"
		return modelAndView;
	}

}
