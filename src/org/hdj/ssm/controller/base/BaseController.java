package org.hdj.ssm.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.hdj.ssm.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

	/**
	 * ʹ��ע�⴦���쳣
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	public String handlerException(HttpServletRequest request,Exception e){
		request.setAttribute("ex", e);
		if(e instanceof CustomException){
			return "error";
		}
		return "error2";
	}
}
