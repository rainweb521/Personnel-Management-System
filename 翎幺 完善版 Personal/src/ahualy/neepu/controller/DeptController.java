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

import ahualy.neepu.pojo.Dept;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;

@Controller
public class DeptController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	
	//如果在目录下输入为空，则跳转到指定链接
	@RequestMapping(value="/dept/")
	 public ModelAndView index2(ModelAndView mv){
		mv.setViewName("dept/list");
		return mv;
	}
	// 如果在目录下输入任何不存在的参数，则跳转到list
	@RequestMapping(value="/dept/{formName}")
	 public String index2(@PathVariable String formName){
		String blank = "/dept/list";
		return blank;
	}
	
	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	 public String index(Integer pageIndex,Model model,String content,Dept dept){
        
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
					
		List<Dept> dept_list = ahualyservice.findAllDept();
		if (content!=null&&!content.equals("")){
			dept_list = ahualyservice.findAllDept(content);
		}
		
		model.addAttribute("list",dept_list);
		model.addAttribute("pageModel", pageModel);
		return "dept/list";
	}
	@RequestMapping(value="/dept/add",method=RequestMethod.GET)
	 public String add(Model model,Integer id){
		if(id!=null){
			Dept dept = ahualyservice.get_Info(id);
			model.addAttribute("dept",dept);
		}
		return "/dept/add";
	}
	
	@RequestMapping(value="/dept/edit",method=RequestMethod.GET)
	 public String edit(Model model,Integer id){
		if(id!=null){
			Dept dept = ahualyservice.get_Info(id);
			model.addAttribute("dept",dept);
		}
		return "/dept/edit";
	}
	
	
	
	
	@RequestMapping(value="/dept/add",method=RequestMethod.POST)
	 public ModelAndView add(ModelAndView mv,@ModelAttribute Dept dept ,Integer id){
		if(id!=null){
			ahualyservice.update_Info(dept);
		}else{
			//首先得确定没有此部门，才能做添加工作
			Dept d = ahualyservice.get_deptByname(dept.getName());
			if(d==null) {
				ahualyservice.addDept(dept);
				mv.setViewName("redirect:/dept/list");
			}else {
				mv.addObject("message", "此部门已经存在！！！");
				mv.setViewName("redirect:/dept/add");
			}
			
		}
		
		return mv;
	}
	@RequestMapping(value="/dept/delete",method=RequestMethod.GET)
	 public void delete(Integer id){
		System.out.println(id);
		if(id!=null){
			ahualyservice.delete_Info(id);
		}
	}
}
