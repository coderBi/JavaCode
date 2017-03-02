/**
 *
 */
package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.po.ItemsCustom;
import ssm.service.ItemsService;

/**
 * <p>
 * Title:JsonTest
 * </p>
 * <p>
 * Description:json数据交互测试
 * </p>
 * 
 * @author Edwin
 * @since 2017-3-2
 * @version 1.0
 */
@Controller
@RequestMapping("/jsonTest")
public class JsonTest {
	@Autowired ItemsService itemsService;
	
	@RequestMapping("/showJsp")
	public String showJsp() {
		return "jsonTest";
	}

	// 请求参数是json字符串。 @RequestBody 将前台传入的json转换到itemsCustom
	// @ResponseBody将返回的ItemsCustom类型转换成json
	@RequestMapping("/requestJson")
	public @ResponseBody
	ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {
		return itemsCustom;
	}

	// 请求参数是鍵值對。由于是键值对，所以不需要@RequestBody
	@RequestMapping("/responseJson")
	public @ResponseBody
	ItemsCustom responseJson(ItemsCustom itemsCustom) {
		return itemsCustom;
	}
	
	//商品查询 使用restful形式的url
	@RequestMapping("/queryItems/{id}")
	public @ResponseBody ItemsCustom queryItems(@PathVariable("id") Integer id) throws Exception{
		return itemsService.findItemsById(id);
	}
}
