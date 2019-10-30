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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ahualy.neepu.pojo.User;
import ahualy.neepu.service.AhualyService;

@Controller
public class FindPasswordCheckController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	
	
	//找回密码之前的验证
			@RequestMapping("checkLoginName")
			@ResponseBody
			public void checkUserName(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
				response.setContentType("application/text;charset=utf-8");
				PrintWriter out = response.getWriter();
		        String loginname = request.getParameter("loginname");
		        String message = null;
		        
		        if(ahualyservice.findUserByLogin(loginname)!=null) {
		        	message = "";
		        	out.write(message);
		        }else{
		        	message ="此登录名并不在本系统，请重新输入";
		        	out.write(message);
		        }
			}
			
			//找回密码之前的验证
					@RequestMapping("checkUsername")
					@ResponseBody
					public void checkPhone(HttpServletRequest request,HttpServletResponse response,Model model,
							@RequestParam("username") String username, 
							@RequestParam("loginname") String loginname) throws IOException{
						response.setContentType("application/text;charset=utf-8");
						PrintWriter out = response.getWriter();
				        String message = null;
				        User user = ahualyservice.findUserByLogin(loginname);
				        
				        String username2=user.getUsername();
				        if(username.equals(username2)) {
				        	message = "";
				        	out.write(message);
				        }else{
				        	message ="用户名和登录名不匹配，请重新输入";
				        	out.write(message);
				        }
					}
}
