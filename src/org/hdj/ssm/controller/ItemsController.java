package org.hdj.ssm.controller;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.hdj.ssm.controller.base.BaseController;
import org.hdj.ssm.controller.validation.GroupValidate1;
import org.hdj.ssm.exception.CustomException;
import org.hdj.ssm.po.ItemsCustom;
import org.hdj.ssm.po.ItemsCustomVo;
import org.hdj.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author H_DJ
 *
 *
 *@RequestMapping������
 *1. ���ڷ��ʵ�ַ��ӳ��
 *2. �������Ʒ���·��
 *@RequestMapping("/Itmes")
 *public class ItemsController 
 *3. �����޶�http���󷽷�
 *@RequestMapping(value="/queryItems",method={RequestMethod.GET,RequestMethod.POST})
 */

@Controller
@RequestMapping("/ItemsInfo")
public class ItemsController extends BaseController{

	
	
	@Autowired
	public ItemService itemService;
	
	/**
	 * ������ҳ���У�ͨ��key:itemTypes����ȡ�÷����ķ���ֵ
	 * @return
	 */
	@ModelAttribute("itemTypes")
	public Map<String,String> getAllTypes(){
		Map<String,String> itemtypes=new HashMap<String, String>();
		itemtypes.put("101", "����");
		itemtypes.put("102", "����");
		return itemtypes;
	}
	
	
	//��ѯ��Ʒ
	@RequestMapping(value="/queryItems",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView queryItems(ItemsCustomVo itemsCustomVo) throws Exception{
//		int m=0;
//		if(m==0) throw new CustomException("����Ϊ�㣡");
//		int i=1/m;
		Collection<ItemsCustom> findItemsList = itemService.findItemsList(itemsCustomVo);
		//����һ��modelAndView ��ǰ��������������������
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("items", findItemsList);
		
		//ָ����Ӧ����ͼ·��
		//modelAndView.setViewName("/WEB-INF/page/items/itemsList.jsp");
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	/**
	 * ��Ʒ���޸�
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editItems")
	public String editItems(Model model,@RequestParam(value="id",defaultValue="1",required=true)Integer itemId) throws Exception{
		
		ItemsCustom itemsCustom=itemService.selectiveByPrimaryId(itemId);
		
//		ModelAndView modelAndView=new ModelAndView();
//		modelAndView.addObject("item",itemsCustom);
//		modelAndView.setViewName("/items/editItem");
		model.addAttribute("item",itemsCustom);
		
		return "/items/editItem";		
	}
		
	/**
	 * ��Ʒ�������޸�ҳ��
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editItemsQuery")
	public String editItemsQuery(Model model,ItemsCustomVo itemsCustomVo) throws Exception{
		
		Collection<ItemsCustom> itemsList = itemService.findItemsList(itemsCustomVo);
		model.addAttribute("items",itemsList);		
		return "/items/editItemsQuery";		
	}
	
	
	/**
	 * ��Ʒ�������޸�
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateItemsList")
	public String updateItemsList(ItemsCustomVo itemsCustomVo) throws Exception{
		itemService.updateItemsList(itemsCustomVo);		
		return "success";		
	}
	
	/**
	 * ע�⣺ @Validated ItemsCustom itemsCustom,BindingResult bindingResult
	 * @Validated ����Ĳ���ΪҪУ��Ĳ�����
	 * BindingResult �� ����У��ʧ�ܵ���Ϣ
	 * @Validated ��BindingResult�ɶԳ��֣�λ�ã�@Validated��ǰ��BindingResult�ں�
	 *���ݻ���
	 *pojo�����ݻ���
	 *1. ��pojo����controller����ʱ��spingmvc���Զ���pojo��pojo������Сд��Ϊkey,����request����
	 *2. ʹ��ע��@ModelAttribute("item");item����ҳ���ȡ��key
	 *3. ʹ��Model 
	 *		model.addAttribute("item",itemsCustom);
	 */
	@RequestMapping("/updateItem")
	public String updateItem(Model model,HttpServletRequest request,Integer id,
			@ModelAttribute("item")@Validated(value={GroupValidate1.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult,MultipartFile item_pic) throws Exception{
		
		//������鲻ͬ��
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("errors", allErrors);
			//model.addAttribute("item",itemsCustom);
			return "/items/editItem";
		}
		//�ļ��ϴ�
		//ȡ���ļ�ԭʼ��
		String originFileName=item_pic.getOriginalFilename();
		if(item_pic!=null && originFileName!=null && originFileName.length()>1){
			//�ļ����ϴ�Ŀ¼
			String destFile="E:\\IT_sourse\\jsp\\temp\\pic\\";
			//�����µ��ļ���
			String  newFileName=UUID.randomUUID()+originFileName.substring(originFileName.lastIndexOf("."));
			//�ϴ��ļ�
			File file=new File(destFile,newFileName);
			item_pic.transferTo(file);
			//�������ݿ�
			itemsCustom.setPic(newFileName);
		}
		
		//����bean���и���
		int i=itemService.updateByPrimaryId(id, itemsCustom);
		if(i<=0){
			return "redirect:/ItemsInfo/editItems.action?id="+id;
		}
		return "success";		
	}
	
	
	/*

	 * @param items_id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception{		
		Integer i=itemService.deleteItems(items_id);
		return "success";		
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/itemViews/{id}")
	@ResponseBody
	public Collection<ItemsCustom> itemViews(@PathVariable("id")Integer id) throws Exception{
		ItemsCustomVo itemsCustomVo=new ItemsCustomVo();
		ItemsCustom itemsCustom=new ItemsCustom();
		itemsCustom.setId(id);
		itemsCustomVo.setItemsCustom(itemsCustom );
		return itemService.findItemsList(itemsCustomVo);
	}
}
