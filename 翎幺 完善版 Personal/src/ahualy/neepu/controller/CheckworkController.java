package ahualy.neepu.controller;

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
import org.springframework.web.servlet.ModelAndView;

import ahualy.neepu.pojo.Checkwork;
import ahualy.neepu.pojo.Dept;
import ahualy.neepu.pojo.Employee;
import ahualy.neepu.pojo.Job;
import ahualy.neepu.service.AhualyService;
import ahualy.neepu.util.page.PageModel;

@Controller
public class CheckworkController {
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	   //如果在目录下输入为空，则跳转到指定链接
		@RequestMapping(value="/checkwork/")
		 public ModelAndView index2(ModelAndView mv){
			mv.setViewName("checkwork/list");
			return mv;
		}
		// 如果在目录下输入任何不存在的参数，则跳转到list
		@RequestMapping(value="/checkwork/{formName}")
		 public String index2(@PathVariable String formName){
			String blank = "/checkwork/list";
			return blank;
		}
		
		@RequestMapping(value="/checkwork/list",method=RequestMethod.GET)
		 public String index(Integer pageIndex,Model model,String content,Checkwork checkwork){
			
			// 创建分页对象
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			
			List<Checkwork> job_list = ahualyservice.get_CheckworkList(checkwork,pageModel);
			Integer count = 0;
			if (content!=null&&!content.equals("")){
				count = ahualyservice.countCheckwork(content);
				job_list = ahualyservice.get_CheckworkLikeList(content);
			}
			model.addAttribute("count",count);
			model.addAttribute("list",job_list);
			model.addAttribute("pageModel", pageModel);
			return "checkwork/list";
		}
		
		
		@RequestMapping(value="/checkwork/add",method=RequestMethod.GET)
		 public String add(Model model,Integer id){
			if(id!=null){
				Checkwork checkwork = ahualyservice.get_CheckworkInfo(id);
				model.addAttribute("job",checkwork);
			}
			List<Dept> dept_list = ahualyservice.findAllDept();
			List<Job> job_list = ahualyservice.findAllJob();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			return "/checkwork/add";
		}
		
		
		@RequestMapping(value="/checkwork/edit",method=RequestMethod.GET)
		 public String edit(Model model,Integer id){
			if(id!=null){
				Checkwork checkwork = ahualyservice.get_CheckworkInfo(id);
				model.addAttribute("checkwork",checkwork);
			}
			List<Dept> dept_list = ahualyservice.findAllDept();
			List<Job> job_list = ahualyservice.findAllJob();
			model.addAttribute("job_list", job_list);
			model.addAttribute("dept_list",dept_list);
			return "/checkwork/edit";
		}
		
		/**
		 * 这个方法实现的时候，插入的时候，需要得到id，这个id必须和登录用户里面的的id关联，所以这里就需要在添加之前必须拿到登录用户的id
		 * 注册登录信息的时候，需要执行的是添加操作，这个时候可以给自动生成一个id，这个id直接插入到数据库
		 * 这里需要添加考核信息的时候，就需要用到这个id，这个id可以直接从用户对象中去获取
		 * 
		 */
		@RequestMapping(value="/checkwork/add",method=RequestMethod.POST)
		 public ModelAndView add(ModelAndView mv,
				 Integer job_id,Integer dept_id,String employee_name,Model model,
				 @ModelAttribute Checkwork checkwork ,Integer id,HttpSession session){
			Integer employee_id =null;
			if(id!=null){
				this.genericAssociation(job_id, dept_id,employee_name,checkwork);
				ahualyservice.update_CheckworkInfo(checkwork);
				
			}else{
				/*1，这里获取需要添加的姓名，但是这个姓名应该已经被添加在员工中，所以这里需要去查询这个姓名
				通过姓名去查询id，如果有，就可以添加到考勤表里面，如果没有，提示用户，没有此员工
				并且保证这个姓名不再考勤列表中，就是通过查到的这个id去查询考勤列表，如果能查到，就说明考勤列表中有，在不能够插入了*/
				
				//首先在员工表中更具用户名去查询
				employee_id = ahualyservice.get_EmployeeByName(employee_name);
				if(employee_id!=null&&ahualyservice.get_CheckworkEmp_id(employee_id)==null) {
					    //根据employee_id去查询员工的信息，前端只需要添加薪资即可。
					    Employee e =  ahualyservice.get_EmployeeInfo(employee_id);
					    Integer job_id1 =e.getJob().getId();
					    Integer dept_id1 = e.getDept().getId();
					    //只需要将id设置为员工信息的id（即全局id即可）
					    checkwork.setId(employee_id);
						this.genericAssociation1(job_id1, dept_id1,employee_id,checkwork);
						ahualyservice.insert_CheckworkInfo(checkwork);
						
						mv.setViewName("redirect:/checkwork/list");
						return mv;
				}else{
					
					mv.setViewName("redirect:/checkwork/add");
					mv.addObject("message", "没有查到此员工，请联系管理员先添加此员工!");
					return mv;
				}
			}
			mv.setViewName("redirect:/checkwork/list");
			return mv;
		}
		
		@RequestMapping(value="/checkwork/delete",method=RequestMethod.GET)
		 public void delete(Integer id){
			System.out.println(id);
			if(id!=null){
				ahualyservice.delete_Checkwork(id);
			}
		}

		//此方法用户关联操作
		private void genericAssociation1(Integer job_id,
				Integer dept_id,Integer employee_id,Checkwork checkwork){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				checkwork.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				checkwork.setDept(dept);
			}
			if(employee_id != null){
				Employee employee= new Employee();
				employee.setId(employee_id);
				checkwork.setEmployee(employee);
			}
			
		}
		
		//此方法用户关联操作
		private void genericAssociation(Integer job_id,
				Integer dept_id,String employee_name,Checkwork checkwork){
			if(job_id != null){
				Job job = new Job();
				job.setId(job_id);
				checkwork.setJob(job);
			}
			if(dept_id != null){
				Dept dept = new Dept();
				dept.setId(dept_id);
				checkwork.setDept(dept);
			}
			if(employee_name != null){
				Employee employee= new Employee();
				employee.setName(employee_name);
				checkwork.setEmployee(employee);
			}
			
		}
}
