package org.hdj.ssm.controller;

import java.util.Collection;
import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemsController {

	@Autowired
	public ItemService itemService;
	//查询商品
		@RequestMapping("/queryItems")
		public ModelAndView queryItems() throws Exception{
			
			Collection<ItemsCustom> findItemsList = itemService.findItemsList(null);
			//创建一个modelAndView 向前端适配器返回请求数据
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("items", findItemsList);
			
			//指定响应的视图路径
			//modelAndView.setViewName("/WEB-INF/page/items/itemsList.jsp");
			modelAndView.setViewName("items/itemsList");
			return modelAndView;
		}
	
}
