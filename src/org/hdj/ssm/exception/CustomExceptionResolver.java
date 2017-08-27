package org.hdj.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * HandlerExceptionResolver : 定义全局异常接口
 * @author H_DJ
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e) {
		/**
		 * 处理异常的流程
		 * 1. 如果是自定义异常，就把异常的消息取出，放入modelAndView中，返回到错误页面
		 * 2. 如果是不是自定义异常，就创建自定义异常，并赋值异常消息，如："未知错误！",然后取出消息，放入modelAndView中
		 */
		//定义一个自定义异常局部变量
		CustomException customException=null;
		//判断异常是否为自定义的异常
		if(e instanceof CustomException){
			customException=(CustomException) e;
		}else{
			customException=new CustomException("未知错误！");
		}
		//取出异常消息，放到ModelAndView中
		String message=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("error");
		return modelAndView;
	}
}
