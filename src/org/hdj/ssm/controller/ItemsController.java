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
	//��ѯ��Ʒ
		@RequestMapping("/queryItems")
		public ModelAndView queryItems() throws Exception{
			
			Collection<ItemsCustom> findItemsList = itemService.findItemsList(null);
			//����һ��modelAndView ��ǰ��������������������
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("items", findItemsList);
			
			//ָ����Ӧ����ͼ·��
			//modelAndView.setViewName("/WEB-INF/page/items/itemsList.jsp");
			modelAndView.setViewName("items/itemsList");
			return modelAndView;
		}
	
}
