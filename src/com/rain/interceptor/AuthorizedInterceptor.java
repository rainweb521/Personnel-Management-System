package com.rain.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rain.domain.User;
import com.rain.util.common.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 判断用户权限的Spring MVC的拦截器
 */
public class AuthorizedInterceptor  implements HandlerInterceptor {

	/** 定义不需要拦截的请求 */
	private static final String[] IGNORE_URI = {"/loginForm", "/login","/404.html"};
	
	 /** 
     * 该方法需要preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后执行，主要作用是用于清理资源。
     */  
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		
	}

	 /** 
     * 这个方法在preHandle方法返回值为true的时候才会执行。
     * 执行时间是在处理器进行处理之 后，也就是在Controller的方法调用之后执行。
     */  
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {
		
	}

	 /** 
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 当preHandle的返回值为false的时候整个请求就结束了。 
     * 如果preHandle的返回值为true，则会继续执行postHandle和afterCompletion。
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		/** 默认用户没有登录 */
		boolean flag = false; 
		/** 获得请求的ServletPath */
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		/**  判断请求是否需要拦截 */
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                System.out.println("*********************");
                break;
            }
        }
        /** 拦截请求 */
        if (!flag){
        	/** 1.获取session中的用户  */
        	User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        	/** 2.判断用户是否已经登录 */
        	if(user == null){
        		 /** 如果用户没有登录，跳转到登录页面 */
        		request.setAttribute("message", "请先登录再访问网站!");
        		request.getRequestDispatcher(Constants.LOGIN).forward(request, response);
        		return flag;
        	}else{
        		 flag = true;
        	}
        }
        return flag;
		
	}

}
