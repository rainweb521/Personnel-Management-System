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

import com.rain.domain.Job;
import com.rain.service.RainService;

@Controller
public class JobController {
	@Autowired
	@Qualifier("RainService")
	private RainService rainservice;
	// 如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/job/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("job/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/job/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/job/list";
			return blank;
		}
		@RequestMapping(value="/job/list",method=RequestMethod.GET)
		 public String index(Model model,String content){
			List<Job> job_list = rainservice.findAllJob();
			if (content!=null){
				job_list = rainservice.findAllJob(content);
			}
			model.addAttribute("list",job_list);
			return "job/list";
		}
		@RequestMapping(value="/job/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Job job = rainservice.get_JobInfo(id);
				model.addAttribute("job",job);
			}
			return "/job/add";
		}
		@RequestMapping(value="/job/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute Job job ,Integer id){
			System.out.println(id);
			if(id!=null){
				rainservice.update_JobInfo(job);
			}else{
				rainservice.insert_JobInfo(job);
			}
			mv.setViewName("redirect:/job/list");
			return mv;
		}
		@RequestMapping(value="/job/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				rainservice.delete_JobInfo(id);
			}
		}
}
