package ahualy.neepu.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ahualy.neepu.pojo.User;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.common.ShiroMD5Privacy;

@Controller
public class FindPasswordController {
	
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	
	@RequestMapping(value="/repassword")
	public String  FindPassword() {
		return "/repasswordPage";
	}
	
	
	
	@RequestMapping(value="/toFindPassword")
	public String toFindPassword(Model model,HttpSession session,User user, @RequestParam("user_input_verifyCode") String user_input_verifyCode) {
		  User user1 = ahualyservice.findUserByLoginAndName(user.getLoginname(), user.getUsername());
		
			  if(!user_input_verifyCode.toLowerCase().equals(session.getAttribute("code"))){
				    model.addAttribute("message", "验证码错误!请重新输入！！！");
					// 服务器内部跳转到登录页面
				    return "/repasswordPage";
			   }else {
				  model.addAttribute("user", user1);
				  return "/findPassword";
			  }
	}
	
	
	@RequestMapping(value="/rePassword")
	public String rePassword(User user) {
		  ahualyservice.toUpdatePassword(user.getLoginname(),ShiroMD5Privacy.privacy(user.getLoginname(), user.getPassword()));
		  return "redirect:/loginForm";
	}
}
