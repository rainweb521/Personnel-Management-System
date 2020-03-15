package ahualy.neepu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ahualy.neepu.pojo.User;
import ahualy.neepu.util.common.Constants;
import ahualy.neepu.util.common.SessionSave;
import io.goeasy.GoEasy;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 判断用户权限的Spring MVC的拦截器
 */
public class AuthorizedInterceptor  implements HandlerInterceptor {

	/** 定义不需要拦截的请求 */
	private static final String[] IGNORE_URI = {"/loginForm", "/login","/repassword","/toFindPassword",
			"/rePassword","/checkCode","/regist","/registCode","/toregistCode","/register","/checkLoginnameAndPassword"
			,"/checkRegistCode","/checkLoginname","/check_Register_loginname","/checkMessage","/check_Register_repassword"
			,"/check_Register_password","/check_Register_email","/check_Register_username","/checkUsername",
			"/checkLoginName"};

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
     * 	preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 	当preHandle的返回值为false的时候整个请求就结束了。 
     * 	如果preHandle的返回值为true，则会继续执行postHandle和afterCompletion。
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		//获取session
		HttpSession session = request.getSession();
		//获取用户请求的url
		//String path = request.getRequestURI();
		//获取session的用户信息
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		
		
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
        	
        	/** 2.判断用户是否已经登录 */
        	if(user == null){
        		 /** 如果用户没有登录，跳转到登录页面 */
        		request.setAttribute("message", "请先登录再访问网站!");
        		request.getRequestDispatcher(Constants.LOGIN).forward(request, response);
        		return flag;
        	}else{
        		String sessionId = SessionSave.getSessionIdSave().get(user.getLoginname());//获取全局类SessionSave保存账户的静态sessionId
    			// 第一次登录的时候，这里不需要去判断，应该直接放行，关键是如何判断这是第一次这个用户登录
        		if(Constants.FIRST ==1) {
        			flag = true;
        		}else {
        			System.out.println("全局sessionid****"+sessionId);
            		String currentSessionId = session.getId();//获取当前的sessionId
            		System.out.println("当前sessionid****"+currentSessionId);
            		
        			if (currentSessionId.equals(sessionId)) {//如果两个sessionId相等，则当前账户强制下线，需要重新登录
        				request.setAttribute("new_message", "您的账号在别的客户端已经登录，先退出，再重新登录!");
        				//这里进行给已登录用户做消息提示，账号信息可能泄露，提示要求更改密码。
        				GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-0706aa93d6614a2db50b660458b42ff5");
        				goEasy.publish("myChannel2","你的账号在异地登录请求已被拦截，登录账号信息可能泄露，如果不是本人操作，请及时更改密码！！！");
        				request.getRequestDispatcher(Constants.LOGIN).forward(request, response);
        			}else {// 如果是同一账户session则放行请求
        				flag = true;
        			}
        		}
        	}
        }
        return flag;
	}

}
