package org.hdj.ssm.mapper;

import java.util.Collection;

import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.po.ItemsCustomVo;
import org.hdj.ssm.po.items;

public interface ItemsMapperCustom {
    
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception;

	public Integer deleteItems(Integer[] items_id) throws Exception;
	
	//ÅúÁ¿ÐÞ¸Ä
		public Integer updateItemsList(ItemsCustomVo itemsCustomVo)throws Exception;
}