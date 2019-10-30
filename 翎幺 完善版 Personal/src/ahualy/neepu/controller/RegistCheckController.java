package ahualy.neepu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ahualy.neepu.service.AhualyService;

@Controller
public class RegistCheckController {
	
	@Autowired
	@Qualifier("AhualyService")
	
	private AhualyService ahualyservice;//接口实现类对象s
	
	
	/**
	 * 注册码非空校验
	 * @param registCode
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value="checkRegistCode",method = RequestMethod.POST)
	@ResponseBody
	public void checkPhnumberandPassword(@RequestParam("registCode") String registCode,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
    	response.setContentType("application/text;charset=utf-8");
		PrintWriter out = response.getWriter();
		String message = null;
    	if(registCode=="") {
    		message = "注册码不能为空,请重新输入";
        	out.write(message);
    	}else {
    		message = "";
    	}
    }
	
	
	/**
	 * 登录名注册校验
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
		@RequestMapping(value="check_Register_loginname",method = RequestMethod.POST)
		@ResponseBody
		public void check_Register_loginname(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
			response.setContentType("application/text;charset=utf-8");
			PrintWriter out = response.getWriter();
	        String loginname = request.getParameter("loginname");
	        String message = null;
	        if(ahualyservice.findUserByLogin(loginname)!=null) {
	        	message = "此登录名已经被注册,请重新输入";
	        	out.write(message);
	        }else if(!loginname.matches("^[A-Za-z]{5,15}$")){
	        	message = "你的登录名不能入住本系统,请您按规则输入";
	        	out.write(message);
	        }else {
	        	message = "";
	        	out.write(message);
	        }
		}
		
		
		
		@RequestMapping(value="check_Register_username",method = RequestMethod.POST)
		@ResponseBody
		public void check_Register_username(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
			response.setContentType("application/text;charset=utf-8");
			
			PrintWriter out = response.getWriter();
	        String username = request.getParameter("username");
	        String message = null;
	        
	        if(ahualyservice.findUserByName(username)!=null) {
	        	message = "此用户名已经被注册,请重新输入";
	        	out.write(message);
	        }else if(!username.matches("^[\\u4E00-\\u9FA5]{2,4}$")){
	        	message = "你的用户名非法不能入住本系统";
	        	out.write(message);
	        }else {
	        	message = "";
	        	out.write(message);
	        }
		}
	
		@RequestMapping(value="check_Register_email",method = RequestMethod.POST)
		@ResponseBody
		public void check_Register_email(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
			response.setContentType("application/text;charset=utf-8");
			
			PrintWriter out = response.getWriter();
	        String email = request.getParameter("email");
	        String message = null;
	        
	        if(ahualyservice.findUserByEmail(email)!=null) {
	        	message = "此邮箱已经被注册,请输入新的邮箱账号";
	        	out.write(message);
	        }else if(!email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")){
	        	message = "你的邮箱非法不能入住本系统";
	        	out.write(message);
	        }else {
	        	message = "";
	        	out.write(message);
	        }
		}
		
		
		@RequestMapping(value="check_Register_password",method = RequestMethod.POST)
		@ResponseBody
		public void check_Register_password(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
			response.setContentType("application/text;charset=utf-8");
			PrintWriter out = response.getWriter();
	        String password = request.getParameter("password");
	        String message = null;
	         if(!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$")){
	        	message = "您的密码非法，请重新输入";
	        	out.write(message);
	        }else {
	        	message = "";
	        	out.write(message);
	        }
		}
		
		
		@RequestMapping(value="check_Register_repassword",method = RequestMethod.POST)
	    @ResponseBody
	    public void check_Register_repassword(@RequestParam("password") String password, 
	    		@RequestParam("repassword") String repassword,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
	    	response.setContentType("application/text;charset=utf-8");
			PrintWriter out = response.getWriter();
			String message = null;
	    	if(password.equals(repassword)) {
	    		message = "";
	        	out.write(message);
	    	}else{
	    		message = "两次输入的密码不相同，请重新输入";
	        	out.write(message);
	    	}
	    }
		
		
	
		@RequestMapping(value="checkMessage",method = RequestMethod.POST)
	    @ResponseBody
	    public void checkMessage(
	    		@RequestParam("password") String password, 
	    		@RequestParam("username") String username,
	    		@RequestParam("loginname") String loginname,
	    		@RequestParam("repassword") String repassword,
	    		@RequestParam("email") String email,
	    		HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
	    	response.setContentType("application/text;charset=utf-8");
			PrintWriter out = response.getWriter();
			String message = null;
	    	if(username==""||loginname==""||email==""||password==""||repassword=="") {
	    		message = "用户名和密码不能为空,请重新输入";
	        	out.write(message);
	    	}
	    	if(username!=""&&loginname!=""&&email!=""&&password!=""&&repassword!="") {
	    		message = "";
	        	out.write(message);
	    	}

	    }
	
}
