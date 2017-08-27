package org.hdj.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.remoting.caucho.HessianClientInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ������
 * @author H_DJ
 *
 */
public class LoginInterceptor  implements HandlerInterceptor{


	/**
	 * ������������ϻص�������������ͼ��Ⱦ���ʱ�ص���
	 * �����ܼ�������ǿ����ڴ˼�¼����ʱ�䲢�������ʱ�䣬
	 * �����Խ���һЩ��Դ����������try-catch-finally�е�finally��
	 * �������ô�����ִ������preHandle����true����������afterCompletion��
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * ����ص�������ʵ�ִ������ĺ���������Ⱦ��ͼ֮ǰ����
	 * ��ʱ���ǿ���ͨ��modelAndView��ģ�ͺ���ͼ���󣩶�ģ�����ݽ��д�������ͼ���д���modelAndViewҲ����Ϊnull��
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mv) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * ���ã� 	Ԥ����ص�������ʵ�ִ�������Ԥ�������¼��飩������������Ϊ��Ӧ�Ĵ�����
	 * ����ֵ��	true��ʾ�������̣��������һ������������������
             	false��ʾ�����жϣ����¼���ʧ�ܣ������������������������������������ʱ������Ҫͨ��response��������Ӧ��
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		// TODO Auto-generated method stub
		//��ȡ����url(һ�㲻��Ҫ���ص�url���������õ������ļ���)
		String url=request.getRequestURI();
		if(url.indexOf("login.action")>0){
			return true;
		}
		//�ж�session���Ƿ�����û�
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		if(username!=null){
			return true;
		}
		//��ת����½ҳ��
		request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
		return false;
	}

}
