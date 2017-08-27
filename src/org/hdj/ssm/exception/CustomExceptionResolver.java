package org.hdj.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * ȫ���쳣������
 * HandlerExceptionResolver : ����ȫ���쳣�ӿ�
 * @author H_DJ
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e) {
		/**
		 * �����쳣������
		 * 1. ������Զ����쳣���Ͱ��쳣����Ϣȡ��������modelAndView�У����ص�����ҳ��
		 * 2. ����ǲ����Զ����쳣���ʹ����Զ����쳣������ֵ�쳣��Ϣ���磺"δ֪����",Ȼ��ȡ����Ϣ������modelAndView��
		 */
		//����һ���Զ����쳣�ֲ�����
		CustomException customException=null;
		//�ж��쳣�Ƿ�Ϊ�Զ�����쳣
		if(e instanceof CustomException){
			customException=(CustomException) e;
		}else{
			customException=new CustomException("δ֪����");
		}
		//ȡ���쳣��Ϣ���ŵ�ModelAndView��
		String message=customException.getMessage();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("error");
		return modelAndView;
	}
}
