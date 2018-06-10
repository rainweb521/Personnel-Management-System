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
import com.rain.domain.Employee;
import com.rain.domain.Job;
import com.rain.service.RainService;

@Controller
public class EmployeeController {
	@Autowired
	@Qualifier("RainService")
	private RainService rainservice;
	// 如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/employee/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("employee/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/employee/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/employee/list";
			return blank;
		}
		@RequestMapping(value="/employee/list",method=RequestMethod.GET)
		 public String index(Model model,String content){
			List<Employee> job_list = rainservice.get_EmployeeList();
			if (content!=null){
				job_list = rainservice.get_EmployeeLikeList(content);
			}
			model.addAttribute("list",job_list);
			return "employee/list";
		}
		@RequestMapping(value="/employee/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Employee employee = rainservice.get_EmployeeInfo(id);
				model.addAttribute("job",employee);
			}
			List<Dept> dept_list = rainservice.findAllDept();
			List<Job> job_list = rainservice.findAllJob();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			return "/employee/add";
		}
		@RequestMapping(value="/employee/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute Employee job ,Integer id){
//			System.out.println(id);
			if(id!=null){
				rainservice.update_EmployeeInfo(job);
			}else{
				rainservice.insert_EmployeeInfo(job);
			}
			mv.setViewName("redirect:/employee/list");
			return mv;
		}
		@RequestMapping(value="/employee/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
//			System.out.println(id);
			if(id!=null){
				rainservice.delete_JobInfo(id);
			}
		}
}
