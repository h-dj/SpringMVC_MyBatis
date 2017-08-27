package org.hdj.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/login",method={RequestMethod.POST})
	public String login(HttpSession session,String username,String password){
		if(username!=null){
			session.setAttribute("username", username);
			//重定向到查询页面
			return "redirect:/ItemsInfo/queryItems";
		}
		return "login";
		
	}
	@RequestMapping(value="/logout",method={RequestMethod.GET})
	public String logout(HttpSession session){
		session.removeAttribute("username");
		return "login";
		
	}
}
