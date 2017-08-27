package org.hdj.ssm.po;

import java.util.List;


public class ItemsCustomVo {
   
	private items item;
	private ItemsCustom itemsCustom;
	
	private List<ItemsCustom> itemList;
	public items getItem() {
		return item;
	}
	public void setItem(items item) {
		this.item = item;
	}
	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}
	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	public List<ItemsCustom> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemsCustom> itemList) {
		this.itemList = itemList;
	}
	
	
	
}