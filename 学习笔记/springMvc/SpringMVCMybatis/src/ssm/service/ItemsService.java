/**
 *
 */
package ssm.service;

import java.util.List;

import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;

/**
 * <p>Title:ItemsService</p>
 * <p>Description:商品查询的service</p>
 * @author Edwin
 * @since 2017-2-26
 * @version 1.0
 */
public interface ItemsService {
	//查询商品
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	/**
	 * 根据id查询商品信息
	 * @param id 查询商品的id
	 * @return
	 * @throws Exception
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception;

	/**
	 * 修改商品信息
	 * @param id 要修改的商品id
	 * @param itemsCustom 修改后的商品信息
	 * @throws Exception
	 */
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
	
	/**
	 * 根据商品id删除商品
	 * @param id 商品id
	 * @throws Exception
	 */
	public void deleteItemsById(Integer id) throws Exception;
}
