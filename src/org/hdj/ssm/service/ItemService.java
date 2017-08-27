package org.hdj.ssm.service;

import java.util.Collection;

import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.po.ItemsCustomVo;
import org.hdj.ssm.po.items;
import org.springframework.stereotype.Service;


public interface ItemService {
	
	//��ѯȫ����Ʒ�ķ���
	public Collection<ItemsCustom> findItemsList(ItemsCustomVo itemsCustomVo) throws Exception;
	
	//ͨ��id��ѯ��Ʒ
	public ItemsCustom selectiveByPrimaryId(Integer id) throws Exception;
	
	//ͨ��id������Ʒ
	public int updateByPrimaryId(Integer id,items item) throws Exception;
	//����ɾ��
	public Integer deleteItems(Integer[] items_id) throws Exception;
	//�����޸�
	public Integer updateItemsList(ItemsCustomVo itemsCustomVo)throws Exception;
}
