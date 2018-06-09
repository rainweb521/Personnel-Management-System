package com.rain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rain.domain.Dept;
import com.rain.service.RainService;

@Controller
public class DeptController {
	@Autowired
	@Qualifier("RainService")
	private RainService rainservice;
	
	// 如果在目录下输入为空，则跳转到指定链接
	@RequestMapping(value="/dept/")
	 public ModelAndView index2(ModelAndView mv){
		mv.setViewName("dept/list");
		return mv;
	}
	// 如果在目录下输入任何不存在的参数，则跳转到list
	@RequestMapping(value="/dept/{formName}")
	 public String index2(@PathVariable String formName){
//		return formName;
		String blank = "/dept/list";
		return blank;
	}
	
	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	 public String index(Model model,String content){
//		System.out.println("4234");
		List<Dept> dept_list = rainservice.findAllDept();
		if (content!=null){
			dept_list = rainservice.findAllDept(content);
		}
		
		model.addAttribute("list",dept_list);
//		for(Dept attribute : dept_list) {
//			  System.out.println(attribute.getName());
//			}
		return "dept/list";
	}
	@RequestMapping(value="/dept/add",method=RequestMethod.GET)
	 public String add(Model model,Integer id){
//		System.out.println(id);
		if(id!=null){
			Dept dept = rainservice.get_Info(id);
			model.addAttribute("dept",dept);
//			System.out.println(dept.getName());
		}
		return "/dept/add";
	}
	@RequestMapping(value="/dept/add",method=RequestMethod.POST)
	 public ModelAndView add(ModelAndView mv,@ModelAttribute Dept dept ,Integer id){
		System.out.println(id);
//		System.out.println(dept.getId());
		if(id!=null){
			rainservice.update_Info(dept);
			System.out.println(dept.getId());
		}else{
			rainservice.addDept(dept);
		}
//		System.out.println(dept.getName());
		mv.setViewName("redirect:/dept/list");
		return mv;
	}
	@RequestMapping(value="/dept/delete",method=RequestMethod.GET)
	 public void delete(Integer id){
		System.out.println(id);
		if(id!=null){
			rainservice.delete_Info(id);
		}
	}
}
