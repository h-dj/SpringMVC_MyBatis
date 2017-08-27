package org.hdj.ssm.service.Impl;

import java.util.Collection;

import org.hdj.ssm.exception.CustomSimpleException;
import org.hdj.ssm.mapper.ItemsMapperCustom;
import org.hdj.ssm.mapper.itemsMapper;
import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.po.ItemsCustomVo;
import org.hdj.ssm.po.items;
import org.hdj.ssm.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemServiceImpl implements ItemService{

	@Autowired
	public ItemsMapperCustom itemsMapperCustom;
	
	@Autowired itemsMapper itemsMapper;
	
	
	@Override
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception{
		/*int m=0;
		if(m==0) throw new CustomSimpleException("除数为零！CustomSimpleException");
		int i=1/m;*/
		return itemsMapperCustom.findItemsList(itemsCustomVo);
	}

	@Override
	public ItemsCustom selectiveByPrimaryId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		items item = itemsMapper.selectByPrimaryKey(id);
		
		ItemsCustom itemsCustom=new ItemsCustom();
		//通过beanUtils把item中的属性复制到ItemsCustom
		BeanUtils.copyProperties(item, itemsCustom);
		
		return itemsCustom;
	}

	@Override
	public int updateByPrimaryId(Integer id, items item) throws Exception {
		// TODO Auto-generated method stub
		
		//先判断条件
		
		//更新
		item.setId(id);		
		return itemsMapper.updateByPrimaryKeyWithBLOBs(item);
	}

	@Override
	public Integer deleteItems(Integer[] items_id) throws Exception{
		// TODO Auto-generated method stub
		return itemsMapperCustom.deleteItems(items_id);
	}

	@Override
	public Integer updateItemsList(ItemsCustomVo itemsCustomVo)
			throws Exception {
		// TODO Auto-generated method stub
		return itemsMapperCustom.updateItemsList(itemsCustomVo);
	}
}
