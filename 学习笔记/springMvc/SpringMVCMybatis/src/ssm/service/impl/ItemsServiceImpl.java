/**
 *
 */
package ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import ssm.exception.CustomException;
import ssm.mapper.ItemsMapper;
import ssm.mapper.ItemsMapperCustom;
import ssm.po.Items;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;
import ssm.service.ItemsService;

/**
 * <p>Title:ItemsServiceImpl</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2017-2-26
 * @version 1.0
 */
public class ItemsServiceImpl implements ItemsService{
	
	//自动注入ItemsMapperCustom 用来进行数据库操作
	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		//通过注入的itemsMapperCustom进行操作
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}


	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		if(items == null){
			//如果没有查询到  抛出异常
			throw new CustomException("商品信息不存在");
		}
		ItemsCustom itemsCustom = new ItemsCustom();
		//将items的属性拷贝到itemsCustom
		//注意org.springframework.beans.BeanUtils下面的拷贝方法是 (src,des)  但是org.apchae.commons.beanutils.BeanUtils下面也有方法copyProperties(des,src)这两个函数名字相同但是参数相反
		BeanUtils.copyProperties(items, itemsCustom); 
		return itemsCustom;
	}

	
	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom)
			throws Exception {
		// 通常需要进行数据校验...

		// 更新商品信息  withBlobs表示对象中间可以有大文本这样的内容
		//保证传入的itemsCustom一定有id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}


	@Override
	public void deleteItemsById(Integer id) throws Exception {
		itemsMapper.deleteByPrimaryKey(id);
	}

}
