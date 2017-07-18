package org.hdj.ssm.service;

import java.util.Collection;

import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.po.ItemsCustomVo;


public interface ItemService {
	
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception;
}
