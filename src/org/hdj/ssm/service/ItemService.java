package org.hdj.ssm.service;

import java.util.Collection;

import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.po.ItemsCustomVo;
import org.hdj.ssm.po.items;
import org.springframework.stereotype.Service;


public interface ItemService {
	
	//查询全部商品的方法
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception;
	
	//通过id查询商品
	public ItemsCustom selectiveByPrimaryId(Integer id) throws Exception;
	
	//通过id更新商品
	public int updateByPrimaryId(Integer id,items item) throws Exception;
	//批量删除
	public Integer deleteItems(Integer[] items_id) throws Exception;
	//批量修改
	public Integer updateItemsList(ItemsCustomVo itemsCustomVo)throws Exception;
}
