/**
 *
 */
package ssm.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ssm.controller.validation.ValidGroup1;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;
import ssm.service.ItemsService;

/**
 * <p>
 * Title:ItemsController
 * </p>
 * <p>
 * Description:商品的Controller
 * </p>
 * 
 * @author Edwin
 * @since 2017-2-26
 * @version 1.0
 */
@Controller
// 窄化请求路径
@RequestMapping("/items")
public class ItemsController {
	// 注入service
	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/queryItems")
	// 传入的参数ItemsQueryVo是包装类型
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo) throws Exception {
		// 调用service查找数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	// 商品信息修改页面的展示
	@RequestMapping("/editItems")
	// 参数id如果没有注解，需要跟前台传入的名称一致，否则可以通过@RequstParam(value="id",required=true[,defaultValue=""])
	// Integer items_id
	public ModelAndView editItems(Integer id) throws Exception {
		// 调用service查询商品
		ItemsCustom itemsCustom = itemsService.findItemsById(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsCustom", itemsCustom);
		modelAndView.setViewName("items/editItems");
		return modelAndView;
	}

	// 商品修改提交
	// 限定请求方法为post
	@RequestMapping(value = "/editItemsSubmit", method = { RequestMethod.POST })
	// itemsCustom对象的填充规则为，如果传入的变量名称与itemsCustom的属性名臣一样，就进行填充。
	public String editItemsSubmit(Integer id, Model model,
			@ModelAttribute("itemsCustom") @Validated ItemsCustom itemsCustom,
			BindingResult bindingResult, MultipartFile items_pic // 接受商品的图片，如果没有添加注解名称items_pic与jsp传入的名称一致。
	) throws Exception {
		// 如果校验有错误 打印信息
		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			// 修改出错，重新进入商品修改页面
			return "items/editItems";
		}

		// 得到原始名称 items_pic即使没有file上传也会存在内部而不为空
		String originalFilename = items_pic.getOriginalFilename();

		// 上传图片 
		if (originalFilename != null && originalFilename.length() > 0) {
			// 存储图片的物理路径
			String picPath = "D:\\WebSever\\apache-tomcat-7.0.57-windows-x86\\apache-tomcat-7.0.57\\uploads\\pic\\";

			// 得到分組目錄名
			String picGroupName = new SimpleDateFormat("yyyyMMdd")
					.format(new Date()) + "\\";

			// 如果分组目录不存在就进行创建
			File picGroupFile = new File(picPath + picGroupName);
			if (!picGroupFile.isDirectory()) {
				picGroupFile.mkdir();
			}

			// 新的图片名称
			String newfileName = UUID.randomUUID()
					+ originalFilename.substring(originalFilename
							.lastIndexOf('.'));

			File newFile = new File(picPath + picGroupName + newfileName);

			// 将内存中数据写入newFile
			items_pic.transferTo(newFile);

			// 将图片位置信息存入数据库字段
			itemsCustom.setPic(picGroupName + newfileName);
		}

		// 调用service
		itemsService.updateItems(id, itemsCustom);
		return "redirect:queryItems.action";
	}

	// 批量删除上商品信息
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception {
		// 调用service批量删除商品
		for (Integer id : items_id) {
			itemsService.deleteItemsById(id);
		}
		return "redirect:queryItems.action";
	}

	// 批量修改商品的页面展示
	@RequestMapping("/editItemsQuery")
	// 传入的参数ItemsQueryVo是包装类型
	public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo)
			throws Exception {
		// 调用service查找数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/editItemsQuery");
		return modelAndView;
	}

	// 批量修改商品
	// 批量商品信息存贮到 itemsQueryVo.itemsList
	@RequestMapping("/updateItemsAllSubmit")
	public String updateItemsAllSubmit(ItemsQueryVo itemsQueryVo)
			throws Exception {
		for (ItemsCustom itemsCustom : itemsQueryVo.getItemsList()) {
			itemsService.updateItems(itemsCustom.getId(), itemsCustom);
		}
		return "redirect:editItemsQuery.action";
	}

	// 得到商品的类型，不映射到url，但是通过 @ModelAttribute注解可以在页面使用这个函数的返回值
	@ModelAttribute("itemsTypes")
	public Map<String, String> getItemsTypes() {
		Map<String, String> itemsTypes = new HashMap<String, String>();
		itemsTypes.put("100", "类型1");
		itemsTypes.put("110", "类型2");
		return itemsTypes;
	}
}
