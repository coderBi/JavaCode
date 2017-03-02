package ssm.mapper;

import java.util.List;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
	// 商品查询
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}