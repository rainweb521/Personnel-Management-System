package com.rain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// 作为一个空方法，可以匹配任何无效输入，再跳转到404
		return formName;
//		String blank = "blank";
//		return blank;
	}
	
	@RequestMapping(value="/")
	 public String index(){
		String blank = "index";
		return blank;
	}
	@RequestMapping(value="/welcome")
	 public String welcome(){
		String blank = "welcome";
		return blank;
	}
}
