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
 *@RequestMapping：解析
 *1. 用于访问地址的映射
 *2. 用于限制访问路径
 *@RequestMapping("/Itmes")
 *public class ItemsController 
 *3. 用于限定http请求方法
 *@RequestMapping(value="/queryItems",method={RequestMethod.GET,RequestMethod.POST})
 */

@Controller
@RequestMapping("/ItemsInfo")
public class ItemsController extends BaseController{

	
	
	@Autowired
	public ItemService itemService;
	
	/**
	 * 可以在页面中，通过key:itemTypes来获取该方法的返回值
	 * @return
	 */
	@ModelAttribute("itemTypes")
	public Map<String,String> getAllTypes(){
		Map<String,String> itemtypes=new HashMap<String, String>();
		itemtypes.put("101", "电子");
		itemtypes.put("102", "服饰");
		return itemtypes;
	}
	
	
	//查询商品
	@RequestMapping(value="/queryItems",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView queryItems(ItemsCustomVo itemsCustomVo) throws Exception{
//		int m=0;
//		if(m==0) throw new CustomException("除数为零！");
//		int i=1/m;
		Collection<ItemsCustom> findItemsList = itemService.findItemsList(itemsCustomVo);
		//创建一个modelAndView 向前端适配器返回请求数据
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("items", findItemsList);
		
		//指定响应的视图路径
		//modelAndView.setViewName("/WEB-INF/page/items/itemsList.jsp");
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	/**
	 * 商品的修改
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
	 * 商品的批量修改页面
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
	 * 商品的批量修改
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateItemsList")
	public String updateItemsList(ItemsCustomVo itemsCustomVo) throws Exception{
		itemService.updateItemsList(itemsCustomVo);		
		return "success";		
	}
	
	/**
	 * 注意： @Validated ItemsCustom itemsCustom,BindingResult bindingResult
	 * @Validated 后面的参数为要校验的参数；
	 * BindingResult ： 接受校验失败的消息
	 * @Validated 和BindingResult成对出现，位置：@Validated在前，BindingResult在后
	 *数据回显
	 *pojo的数据回显
	 *1. 当pojo传到controller方法时，spingmvc会自动把pojo以pojo的类名小写作为key,存入request域中
	 *2. 使用注解@ModelAttribute("item");item：在页面获取的key
	 *3. 使用Model 
	 *		model.addAttribute("item",itemsCustom);
	 */
	@RequestMapping("/updateItem")
	public String updateItem(Model model,HttpServletRequest request,Integer id,
			@ModelAttribute("item")@Validated(value={GroupValidate1.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult,MultipartFile item_pic) throws Exception{
		
		//如果检验不同过
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("errors", allErrors);
			//model.addAttribute("item",itemsCustom);
			return "/items/editItem";
		}
		//文件上传
		//取出文件原始名
		String originFileName=item_pic.getOriginalFilename();
		if(item_pic!=null && originFileName!=null && originFileName.length()>1){
			//文件的上传目录
			String destFile="E:\\IT_sourse\\jsp\\temp\\pic\\";
			//生成新的文件名
			String  newFileName=UUID.randomUUID()+originFileName.substring(originFileName.lastIndexOf("."));
			//上传文件
			File file=new File(destFile,newFileName);
			item_pic.transferTo(file);
			//存入数据库
			itemsCustom.setPic(newFileName);
		}
		
		//传入bean进行更新
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
