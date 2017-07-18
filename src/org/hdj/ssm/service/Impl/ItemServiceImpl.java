package org.hdj.ssm.service.Impl;

import java.util.Collection;

import org.hdj.ssm.mapper.ItemsMapperCustom;
import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.po.ItemsCustomVo;
import org.hdj.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemServiceImpl implements ItemService{

	@Autowired
	public ItemsMapperCustom itemsMapperCustom;
	
	@Override
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception{
		return itemsMapperCustom.findItemsList(itemsCustomVo);
	}
}
