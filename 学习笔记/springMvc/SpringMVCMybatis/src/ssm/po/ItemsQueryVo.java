/**
 *
 */
package ssm.po;

import java.util.List;

/**
 * <p>Title:ItemsQueryVo</p>
 * <p>Description:商品的包装对象，从表现层到持久层进行传输</p>
 * @author Edwin
 * @since 2017-2-26
 * @version 1.0
 */
public class ItemsQueryVo {
	//原生的items也放到里面，用于有时候可能会用到
	private Items items;
	
	//为了系统的可扩展性，一般再对原始的po进行扩展
	private ItemsCustom itemsCustom;
	
	//放一个list的属性，用于Controller方法接受list类型的参数
	List<ItemsCustom> itemsList;

	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
}
