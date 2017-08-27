package org.hdj.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.remoting.caucho.HessianClientInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器
 * @author H_DJ
 *
 */
public class LoginInterceptor  implements HandlerInterceptor{


	/**
	 * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
	 * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
	 * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
	 * 但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），
	 * 此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mv) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 作用： 	预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器
	 * 返回值：	true表示继续流程（如调用下一个拦截器或处理器）；
             	false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		// TODO Auto-generated method stub
		//获取公共url(一般不需要拦截的url，都会配置到配置文件中)
		String url=request.getRequestURI();
		if(url.indexOf("login.action")>0){
			return true;
		}
		//判断session中是否存在用户
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		if(username!=null){
			return true;
		}
		//跳转到登陆页面
		request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
		return false;
	}

}
