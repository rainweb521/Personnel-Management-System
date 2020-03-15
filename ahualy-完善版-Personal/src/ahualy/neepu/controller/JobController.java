package ahualy.neepu.controller;

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

import ahualy.neepu.pojo.Job;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;

@Controller
public class JobController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	   //如果在目录下输入为空，则跳转到指定链接
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
		 public String index(Integer pageIndex,Model model,String content,Job job){

			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Job> job_list = ahualyservice.findAllJob();
			if (content!=null&&!content.equals("")){
				job_list = ahualyservice.findAllJob(content);
			}
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "job/list";
		}
		@RequestMapping(value="/job/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Job job = ahualyservice.get_JobInfo(id);
				model.addAttribute("job",job);
			}
			return "/job/add";
		}
		@RequestMapping(value="/job/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				Job job = ahualyservice.get_JobInfo(id);
				model.addAttribute("job",job);
			}
			return "/job/edit";
		}
		@RequestMapping(value="/job/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,@ModelAttribute Job job ,Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.update_JobInfo(job);
			}else{
				//首先判断是否已经存在这个工作岗位
				Job j = ahualyservice.get_jobByname(job.getName());
				if(j==null) {
					ahualyservice.insert_JobInfo(job);
					mv.setViewName("redirect:/job/list");
				}else {
					mv.addObject("message", "此工作岗位已经存在！！！");
					mv.setViewName("redirect:/job/add");
				}
				
			}
			
			return mv;
		}
		@RequestMapping(value="/job/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.delete_JobInfo(id);
			}
		}
}
