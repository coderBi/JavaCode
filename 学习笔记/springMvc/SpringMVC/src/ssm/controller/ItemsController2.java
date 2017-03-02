/**
 *
 */
package ssm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import ssm.po.Items;

/**
 * <p>
 * Title:Items
 * </p>
 * <p>
 * Description:实现HttpRequestHandler接口的处理器
 * </p>
 * 
 * @author Edwin
 * @since 2017-2-21
 * @version 1.0
 */
public class ItemsController2 implements HttpRequestHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.HttpRequestHandler#handleRequest(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用service查找数据库，查询商品列表
		List<Items> itemsList = new ArrayList<Items>();

		// 向list中填充静态数据
		Items item_1 = new Items();
		item_1.setId(1);
		item_1.setName("笔记本");
		item_1.setPrice(100f);
		item_1.setDetail("一个笔记本而已");
		try {
			item_1.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse("1111-01-01 00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Items item_2 = new Items();
		item_2.setId(2);
		item_2.setName("苹果");
		item_2.setPrice(5000f);
		item_2.setDetail("这是一个很贵的苹果");
		try {
			item_2.setCreatetime(new SimpleDateFormat("yyyy-MM-dd")
					.parse("2222-01-01 00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		itemsList.add(item_1);
		itemsList.add(item_2);
		
		//添加到request域
		request.setAttribute("itemsList", itemsList);
		
		//转发到 jsp页面
		
		request.getRequestDispatcher("WEB-INF/jsp/items/itemsList.jsp").forward(request, response); //这里是原生的操作request，所以视图解析器中的配置前后缀对它无影响
		
		//这种实现HttpRequestHandler由于比较原始，直接操作request与response，所以可以控制返回的格式进行细节控制，例如返回json
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().write("要书写的json字符串");
	}

}
