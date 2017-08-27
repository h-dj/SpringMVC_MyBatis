package org.hdj.ssm.controller;

import org.hdj.ssm.po.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JosnTest {

	/**
	 * @ResponseBody用来把pojo转换位json，或把json转换为pojo
	 * @param itemsCustom
	 * @return
	 */
	@RequestMapping("/requestJson")
	 public @ResponseBody ItemsCustom  requestJson(@RequestBody ItemsCustom itemsCustom){
		return itemsCustom;
	}
	@RequestMapping("/requestKeyValue")
	 public @ResponseBody ItemsCustom  requestKeyValue(ItemsCustom itemsCustom){
		return itemsCustom;
	}
}
