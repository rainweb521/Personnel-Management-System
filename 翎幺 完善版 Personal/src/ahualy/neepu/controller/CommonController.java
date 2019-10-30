package ahualy.neepu.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author anAn
 * 2019年3月23日 下午8:08:47
 * 如果请求路径中的参数token值和登录 时候生成的不一致时，应该报错，不给结果
 */


@Controller
public class CommonController {
	
	
	
	/*@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName,String token,HttpSession session){
		String blank = "index";
		if(formName.equals(blank)&&session.getAttribute("token")!=null&&session.getAttribute("token").equals(token)) {
			System.out.println("token========"+token);
			return blank;
		}else {
			return formName;
		}
		
	}*/
	
	@RequestMapping(value="/index")
	 public String index(String token,HttpSession session,String lang){
		String blank = "index";
		if(session.getAttribute("token")!=null&&session.getAttribute("token").equals(token)
				&&session.getAttribute("lang")!=null&&session.getAttribute("lang").equals(lang)) {
			return blank;
		}else {
			return "error";
		}
	}
	
	@RequestMapping(value="/welcome")
	 public String welcome(){
		String blank = "welcome";
		return blank;
	}
	
	
	@RequestMapping(value="/loginForm")
	 public String loginForm(){
		String blank = "loginForm";
		return blank;
	}
}
