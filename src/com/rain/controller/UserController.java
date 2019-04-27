package com.rain.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rain.domain.Employee;
import com.rain.domain.Job;
import com.rain.domain.Notice;
import com.rain.domain.User;
import com.rain.service.RainService;
import com.rain.util.common.Constants;

@Controller
public class UserController {
	@Autowired
	@Qualifier("RainService")
	private RainService rainservice;
	// 如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/user/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("/user/list");
			return mv;
		}
//		退出功能
		@RequestMapping(value="/user/logout")
		 public ModelAndView logout(ModelAndView mv, HttpSession session){
			session.setAttribute(Constants.USER_SESSION, null);
			session.setAttribute("tip", null);
			mv.setViewName("redirect:/index");
			
			return mv;
		}
		@RequestMapping(value="/login")
		 public ModelAndView login(@RequestParam("loginname") String loginname,
				 @RequestParam("password") String password,@RequestParam("tip") String tip,
				 HttpSession session,
				 ModelAndView mv){
			// 调用业务逻辑组件判断用户是否可以登录
			boolean flag = false;
			if("1".equals(tip)) {
				User user = rainservice.login(loginname, password);
				if(user!=null){
					// 将用户保存到HttpSession当中
					System.out.println("HttpSession");
					session.setAttribute(Constants.USER_SESSION, user);
					session.setAttribute("tip", "1");
					// 客户端跳转到main页面
					mv.setViewName("redirect:/index");
				}else{
					// 设置登录失败提示信息
					System.out.println("设置登录失败提示信息");
					mv.addObject("message", "登录名或密码错误!请重新输入");
					// 服务器内部跳转到登录页面
					mv.setViewName("forward:/loginForm");
				}
			}else {
				Employee user = rainservice.login2(loginname, password);
				if(user!=null){
					// 将用户保存到HttpSession当中
					System.out.println("HttpSession");
					session.setAttribute(Constants.USER_SESSION, user);
					session.setAttribute("tip", "2");
					// 客户端跳转到main页面
					mv.setViewName("redirect:/indexcustomer/");
				}else{
					// 设置登录失败提示信息
					System.out.println("设置登录失败提示信息");
					mv.addObject("message", "登录名或密码错误!请重新输入");
					// 服务器内部跳转到登录页面
					mv.setViewName("forward:/loginForm");
				}
				
			}
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/user/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/user/list";
			return blank;
		}
		@RequestMapping(value="/user/list",method=RequestMethod.GET)
		 public String index(Model model,String content){
			List<User> job_list = rainservice.get_UserList();
			if (content!=null){
				job_list = rainservice.get_UserLikeList(content);
			}
			model.addAttribute("list",job_list);
			return "user/list";
		}
		@RequestMapping(value="/user/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				User job = rainservice.get_UserInfo(id);
				model.addAttribute("job",job);
			}
			return "/user/add";
		}
		@RequestMapping(value="/user/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute User notice ,Integer id){
			System.out.println(id);
			if(id!=null){
				rainservice.update_UserInfo(notice);
			}else{
				rainservice.insert_UserInfo(notice);
			}
			mv.setViewName("redirect:/user/list");
			return mv;
		}
		@RequestMapping(value="/user/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				rainservice.delete_UserInfo(id);
			}
		}
//		管理员自己修改密码时跳转的页面 
		@RequestMapping(value="/user/myupdate",method=RequestMethod.GET)
		 public String update(Model model,HttpSession session){
			User user = (User) session.getAttribute(Constants.USER_SESSION);
			model.addAttribute("job",user);
			return "/user/myupdate";
		}
		@RequestMapping(value="/user/myupdate",method=RequestMethod.POST)
		 public ModelAndView update(ModelAndView mv,Model model,HttpSession session,User notice){
			User user = (User) session.getAttribute(Constants.USER_SESSION);
//			如果是自己修改自己的密码，则更新session
			user.setLoginname(notice.getLoginname());
			user.setPassword(notice.getPassword());
			user.setUsername(notice.getUsername());
			rainservice.update_UserInfo(user);
				session.setAttribute(Constants.USER_SESSION, user);
				mv.setViewName("redirect:/user/myupdate");
				return mv;
		}
}
